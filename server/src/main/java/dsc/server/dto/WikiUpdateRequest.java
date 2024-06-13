package dsc.server.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record WikiUpdateRequest(
    UUID metaId,
    List<WikiInfo> updateWikiInfos
) {
    public record WikiInfo(
            String title,
            String country,
            String uri,
            int editCount,
            Double ewma,
            LocalDateTime editedAt
    ) {}
}
