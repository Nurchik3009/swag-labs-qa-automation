package guru.qa.api.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import guru.qa.api.models.clubs.ClubBodyModel;
import guru.qa.api.models.clubs.ClubModel;
import guru.qa.api.models.clubs.ClubPatchBodyModel;
import guru.qa.api.models.clubs.ClubsListResponseModel;

import static io.restassured.RestAssured.given;
import static guru.qa.api.specs.clubs.ClubsSpec.*;

public class ClubsApiClient {

    private RequestSpecification authorizedRequest(String token) {
        var request = given(clubsRequestSpec);
        if (token != null && !token.isEmpty()) {
            request.header("Authorization", "Bearer " + token);
        }
        return request;
    }

    @Step("Получение списка клубов GET /clubs/")
    public ClubsListResponseModel getClubs() {
        return given(clubsRequestSpec)
                .when()
                .get("/clubs/")
                .then()
                .spec(successfulClubsListResponseSpec)
                .extract()
                .as(ClubsListResponseModel.class);
    }

    @Step("Получение клуба по id GET /clubs/{id}/")
    public ClubModel getClub(int clubId) {
        return given(clubsRequestSpec)
                .when()
                .get("/clubs/{id}/", clubId)
                .then()
                .spec(successfulClubResponseSpec)
                .extract()
                .as(ClubModel.class);
    }

    @Step("Получение клуба по id с произвольной спецификацией")
    public Response getClubWithSpec(int clubId, ResponseSpecification spec) {
        return given(clubsRequestSpec)
                .when()
                .get("/clubs/{id}/", clubId)
                .then()
                .spec(spec)
                .extract()
                .response();
    }

    @Step("Создание клуба POST /clubs/")
    public ClubModel createClub(String token, ClubBodyModel body) {
        return authorizedRequest(token)
                .body(body)
                .when()
                .post("/clubs/")
                .then()
                .spec(createdClubResponseSpec)
                .extract()
                .as(ClubModel.class);
    }

    @Step("Создание клуба с произвольной спецификацией")
    public Response createClubWithSpec(String token, ClubBodyModel body, ResponseSpecification spec) {
        return authorizedRequest(token)
                .body(body)
                .when()
                .post("/clubs/")
                .then()
                .spec(spec)
                .extract()
                .response();
    }

    @Step("Полное обновление клуба PUT /clubs/{id}/")
    public ClubModel updateClub(String token, int clubId, ClubBodyModel body) {
        return authorizedRequest(token)
                .body(body)
                .when()
                .put("/clubs/{id}/", clubId)
                .then()
                .spec(successfulClubResponseSpec)
                .extract()
                .as(ClubModel.class);
    }

    @Step("Полное обновление клуба с произвольной спецификацией")
    public Response updateClubWithSpec(String token, int clubId, ClubBodyModel body, ResponseSpecification spec) {
        return authorizedRequest(token)
                .body(body)
                .when()
                .put("/clubs/{id}/", clubId)
                .then()
                .spec(spec)
                .extract()
                .response();
    }

    @Step("Частичное обновление клуба PATCH /clubs/{id}/")
    public ClubModel patchClub(String token, int clubId, ClubPatchBodyModel body) {
        return authorizedRequest(token)
                .body(body)
                .when()
                .patch("/clubs/{id}/", clubId)
                .then()
                .spec(successfulClubResponseSpec)
                .extract()
                .as(ClubModel.class);
    }

    @Step("Удаление клуба DELETE /clubs/{id}/")
    public void deleteClub(String token, int clubId) {
        authorizedRequest(token)
                .when()
                .delete("/clubs/{id}/", clubId)
                .then()
                .spec(deletedClubResponseSpec);
    }

    @Step("Удаление клуба с произвольной спецификацией")
    public Response deleteClubWithSpec(String token, int clubId, ResponseSpecification spec) {
        return authorizedRequest(token)
                .when()
                .delete("/clubs/{id}/", clubId)
                .then()
                .spec(spec)
                .extract()
                .response();
    }
}