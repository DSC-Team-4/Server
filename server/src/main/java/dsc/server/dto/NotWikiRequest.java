package dsc.server.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotWikiRequest(
        String title,
        String country,
        UUID metaId,
        String uri,
        LocalDateTime editedAt
) {
}
