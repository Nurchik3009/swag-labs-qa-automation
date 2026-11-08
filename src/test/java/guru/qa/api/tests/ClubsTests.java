package guru.qa.api.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import guru.qa.api.models.clubs.ClubBodyModel;
import guru.qa.api.models.clubs.ClubModel;
import guru.qa.api.models.clubs.ClubPatchBodyModel;
import guru.qa.api.models.clubs.ClubsListResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static guru.qa.api.specs.clubs.ClubsSpec.*;
import static guru.qa.api.tests.TestData.*;

@Feature("Клубы")
@Story("CRUD /api/v1/clubs/")
@Tag("api")
public class ClubsTests extends TestBase {

    private String accessToken;

    @BeforeEach
    public void auth() {
        Map<String, String> loginData = Map.of(
                "username", LOGIN_USERNAME,
                "password", LOGIN_PASSWORD
        );
        accessToken = api.auth.loginAndGetAccessToken(loginData);
    }

    private ClubBodyModel uniqueClubBody() {
        String suffix = String.valueOf(System.currentTimeMillis());
        return new ClubBodyModel(
                "QA Guru Club " + suffix,
                "Stanislav Vasenkov",
                2026,
                "Homework CRUD club",
                "https://t.me/qa_guru_" + suffix
        );
    }

    @Test
    @DisplayName("Read: список клубов возвращает 200 и корректную структуру")
    public void getClubsReturns200AndValidStructure() {
        ClubsListResponseModel response = api.clubs.getClubs();

        step("Проверить пагинацию и обязательные поля клубов на странице", () -> {
            assertThat(response).isNotNull();
            assertThat(response.count()).isGreaterThanOrEqualTo(0);
            assertThat(response.results()).isNotNull();
            assertThat(response.results().size()).isLessThanOrEqualTo(response.count());

            for (ClubModel club : response.results()) {
                assertThat(club.id()).isNotNull().isPositive();
                assertThat(club.bookTitle()).isNotBlank();
                assertThat(club.bookAuthors()).isNotBlank();
                assertThat(club.publicationYear()).isNotNull();
                assertThat(club.description()).isNotNull();
                assertThat(club.telegramChatLink()).isNotNull();
                assertThat(club.owner()).isNotNull().isPositive();
                assertThat(club.members()).isNotNull();
                assertThat(club.reviews()).isNotNull();
                assertThat(club.created()).isNotNull();
            }
        });
    }

    @Test
    @DisplayName("Read: получение клуба по id возвращает тот же клуб, что и в списке")
    public void getClubByIdReturnsSameClubAsInList() {
        ClubsListResponseModel listResponse = api.clubs.getClubs();
        ClubModel clubFromList = listResponse.results().get(0);

        ClubModel clubById = api.clubs.getClub(clubFromList.id());

        step("Проверить, что данные клуба совпадают", () -> {
            assertThat(clubById.id()).isEqualTo(clubFromList.id());
            assertThat(clubById.bookTitle()).isEqualTo(clubFromList.bookTitle());
            assertThat(clubById.bookAuthors()).isEqualTo(clubFromList.bookAuthors());
            assertThat(clubById.owner()).isEqualTo(clubFromList.owner());
        });
    }

    @Test
    @DisplayName("Read: получение несуществующего клуба (404 Not Found)")
    public void getNonExistentClubReturns404() {
        var response = api.clubs.getClubWithSpec(NON_EXISTENT_CLUB_ID, clubsResponse404Spec);

        step("Проверить сообщение об ошибке 404 Not Found", () ->
                assertThat(response.path("detail").toString()).isEqualTo(NOT_FOUND_ERROR)
        );
    }

    @Test
    @DisplayName("Create: успешное создание клуба (201 Created)")
    public void createClubReturns201AndPersistsData() {
        ClubBodyModel clubData = uniqueClubBody();

        ClubModel created = api.clubs.createClub(accessToken, clubData);

        step("Проверить, что клуб создан с переданными данными", () -> {
            assertThat(created.id()).isPositive();
            assertThat(created.bookTitle()).isEqualTo(clubData.bookTitle());
            assertThat(created.bookAuthors()).isEqualTo(clubData.bookAuthors());
            assertThat(created.publicationYear()).isEqualTo(clubData.publicationYear());
            assertThat(created.description()).isEqualTo(clubData.description());
            assertThat(created.telegramChatLink()).isEqualTo(clubData.telegramChatLink());
            assertThat(created.owner()).isPositive();
            assertThat(created.members()).contains(created.owner());
        });

        api.clubs.deleteClub(accessToken, created.id());
    }

    @Test
    @DisplayName("Create: создание клуба без токена (401 Unauthorized)")
    public void createClubWithoutTokenReturns401() {
        ClubBodyModel clubData = uniqueClubBody();

        var response = api.clubs.createClubWithSpec(EMPTY_STRING, clubData, clubsResponse401Spec);

        step("Проверить сообщение об ошибке авторизации (401 Unauthorized)", () ->
                assertThat(response.path("detail").toString()).isEqualTo(UNAUTHORIZED_ERROR)
        );
    }

    @Test
    @DisplayName("Create: создание клуба с пустым названием (400 Bad Request)")
    public void createClubWithEmptyTitleReturns400() {
        ClubBodyModel valid = uniqueClubBody();
        ClubBodyModel clubData = new ClubBodyModel(
                EMPTY_STRING,
                valid.bookAuthors(),
                valid.publicationYear(),
                valid.description(),
                valid.telegramChatLink()
        );

        var response = api.clubs.createClubWithSpec(accessToken, clubData, clubsResponse400Spec);

        step("Проверить валидационную ошибку для поля bookTitle (400)", () ->
                assertThat(response.path("bookTitle[0]").toString()).isEqualTo(FIELD_REQUIRED_ERROR)
        );
    }

    @Test
    @DisplayName("Create: создание клуба с невалидной ссылкой Telegram (400 Bad Request)")
    public void createClubWithInvalidTelegramLinkReturns400() {
        ClubBodyModel valid = uniqueClubBody();
        ClubBodyModel clubData = new ClubBodyModel(
                valid.bookTitle(),
                valid.bookAuthors(),
                valid.publicationYear(),
                valid.description(),
                INVALID_TELEGRAM_LINK
        );

        var response = api.clubs.createClubWithSpec(accessToken, clubData, clubsResponse400Spec);

        step("Проверить валидационную ошибку URL (400)", () ->
                assertThat(response.path("telegramChatLink[0]").toString()).isEqualTo(INVALID_URL_ERROR)
        );
    }

    @Test
    @DisplayName("Update: успешное полное обновление клуба PUT (200 OK)")
    public void updateClubPutReturns200AndNewData() {
        ClubBodyModel createData = uniqueClubBody();
        ClubModel created = api.clubs.createClub(accessToken, createData);

        ClubBodyModel updateData = new ClubBodyModel(
                createData.bookTitle() + " Updated",
                "Updated Author",
                2025,
                UPDATED_CLUB_DESCRIPTION,
                createData.telegramChatLink()
        );

        ClubModel updated = api.clubs.updateClub(accessToken, created.id(), updateData);

        step("Проверить, что данные клуба обновились", () -> {
            assertThat(updated.id()).isEqualTo(created.id());
            assertThat(updated.bookTitle()).isEqualTo(updateData.bookTitle());
            assertThat(updated.bookAuthors()).isEqualTo(updateData.bookAuthors());
            assertThat(updated.publicationYear()).isEqualTo(updateData.publicationYear());
            assertThat(updated.description()).isEqualTo(updateData.description());
        });

        api.clubs.deleteClub(accessToken, created.id());
    }

    @Test
    @DisplayName("Update: успешное частичное обновление клуба PATCH (200 OK)")
    public void patchClubReturns200AndNewDescription() {
        ClubBodyModel createData = uniqueClubBody();
        ClubModel created = api.clubs.createClub(accessToken, createData);

        ClubModel patched = api.clubs.patchClub(accessToken, created.id(), new ClubPatchBodyModel(UPDATED_CLUB_DESCRIPTION));

        step("Проверить, что описание обновилось, а остальные поля не изменились", () -> {
            assertThat(patched.id()).isEqualTo(created.id());
            assertThat(patched.description()).isEqualTo(UPDATED_CLUB_DESCRIPTION);
            assertThat(patched.bookTitle()).isEqualTo(created.bookTitle());
            assertThat(patched.bookAuthors()).isEqualTo(created.bookAuthors());
            assertThat(patched.publicationYear()).isEqualTo(created.publicationYear());
        });

        api.clubs.deleteClub(accessToken, created.id());
    }

    @Test
    @DisplayName("Update: обновление клуба без токена (401 Unauthorized)")
    public void updateClubWithoutTokenReturns401() {
        ClubBodyModel clubData = uniqueClubBody();

        var response = api.clubs.updateClubWithSpec(EMPTY_STRING, 1, clubData, clubsResponse401Spec);

        step("Проверить сообщение об ошибке авторизации (401 Unauthorized)", () ->
                assertThat(response.path("detail").toString()).isEqualTo(UNAUTHORIZED_ERROR)
        );
    }

    @Test
    @DisplayName("Delete: успешное удаление клуба (204) и последующий 404 при чтении")
    public void deleteClubReturns204AndThenNotFound() {
        ClubBodyModel clubData = uniqueClubBody();
        ClubModel created = api.clubs.createClub(accessToken, clubData);

        api.clubs.deleteClub(accessToken, created.id());

        var response = api.clubs.getClubWithSpec(created.id(), clubsResponse404Spec);

        step("Проверить, что клуб больше не найден (404)", () ->
                assertThat(response.path("detail").toString()).isEqualTo(NOT_FOUND_ERROR)
        );
    }

    @Test
    @DisplayName("Delete: удаление клуба без токена (401 Unauthorized)")
    public void deleteClubWithoutTokenReturns401() {
        var response = api.clubs.deleteClubWithSpec(EMPTY_STRING, 1, clubsResponse401Spec);

        step("Проверить сообщение об ошибке авторизации (401 Unauthorized)", () ->
                assertThat(response.path("detail").toString()).isEqualTo(UNAUTHORIZED_ERROR)
        );
    }

    @Test
    @DisplayName("Delete: удаление несуществующего клуба (404 Not Found)")
    public void deleteNonExistentClubReturns404() {
        var response = api.clubs.deleteClubWithSpec(accessToken, NON_EXISTENT_CLUB_ID, clubsResponse404Spec);

        step("Проверить сообщение об ошибке 404 Not Found", () ->
                assertThat(response.path("detail").toString()).isEqualTo(NOT_FOUND_ERROR)
        );
    }
}