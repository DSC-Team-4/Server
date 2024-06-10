package dsc.server.dto;

import java.time.LocalDateTime;

public record SearchDto(
        String search,
        String period,
        String country,
        String category
) {
}
