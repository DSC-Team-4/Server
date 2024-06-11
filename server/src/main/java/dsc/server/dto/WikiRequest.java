package dsc.server.dto;

public record WikiRequest(
        String title,
        String country,
        String url,
        String editedAt
) {
}
