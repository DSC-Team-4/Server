package dsc.server.dto;

public record SearchRequest(
        String period,
        String country
) {
}
