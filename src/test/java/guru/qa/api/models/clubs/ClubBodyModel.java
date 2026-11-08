package guru.qa.api.models.clubs;

/**
 * Тело запроса на создание и полное обновление клуба
 * (POST /clubs/, PUT /clubs/{id}/).
 */
public record ClubBodyModel(
        String bookTitle,
        String bookAuthors,
        Integer publicationYear,
        String description,
        String telegramChatLink
) {}
