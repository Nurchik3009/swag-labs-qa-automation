package guru.qa.api.models.clubs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Модель клуба в ответах API (список, получение, создание, обновление).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ClubModel(
        Integer id,
        String bookTitle,
        String bookAuthors,
        Integer publicationYear,
        String description,
        String telegramChatLink,
        Integer owner,
        List<Integer> members,
        List<ClubReviewModel> reviews,
        String created,
        String modified
) {}
