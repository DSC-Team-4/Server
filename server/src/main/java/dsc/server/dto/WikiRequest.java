package dsc.server.dto;

import java.util.UUID;

public record WikiRequest(
        String title,
        String country,
        UUID metaId,
        String uri,
        String editedAt
) {
}
