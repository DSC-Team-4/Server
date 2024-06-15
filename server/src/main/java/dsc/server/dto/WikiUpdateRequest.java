package dsc.server.dto;

import java.util.UUID;

public record WikiUpdateRequest(
    UUID metaId,
    Double ewma
) {
}
