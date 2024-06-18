package dsc.server.dto;

import java.time.LocalDateTime;

public record NotWikiRequest(
        String title,
        String country,
        Long metaId,
        String uri,
        LocalDateTime editedAt
) {
}
