package dsc.server.dto;

public record SearchRequest(
        String search,
        String period,
        String country
) {
}
