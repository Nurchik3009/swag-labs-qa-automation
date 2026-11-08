package guru.qa.api.models.clubs;

/**
 * Тело частичного обновления клуба (PATCH /clubs/{id}/).
 */
public record ClubPatchBodyModel(String description) {}
