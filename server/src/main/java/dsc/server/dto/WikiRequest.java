package dsc.server.dto;

public record WikiRequest(
        String title,
        String country,
        String uri,
        String editedAt
) {
}
