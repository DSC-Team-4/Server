package dsc.server.dto;

import dsc.server.entity.NotEwmaWiki;
import dsc.server.entity.Wiki;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public record WikiResponse(
        Long id,
        String title,
        String country,
        String uri,
        Long metaId,
        Double ewma,
        int editCount,
        LocalDateTime editedAt
) {
    public static List<WikiResponse> ofList(List<Wiki> wikis) {

        return wikis.stream().map(wiki -> new WikiResponse(
                wiki.getId(),
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getMetaId(),
                wiki.getEwma(),
                wiki.getEditCount(),
                wiki.getEditedAt())
        ).toList();
    }

    public static List<WikiResponse> ofList(List<Wiki> wikis, int maxCount) {

        return wikis.stream().map(wiki -> new WikiResponse(
                wiki.getId(),
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getMetaId(),
                wiki.getEwma(),
                wiki.getEditCount(),
                wiki.getEditedAt())
        ).limit(maxCount).toList();
    }

    public static WikiResponse of(Wiki wiki) {
        return new WikiResponse(
                wiki.getId(),
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getMetaId(),
                wiki.getEwma(),
                wiki.getEditCount(),
                wiki.getEditedAt()
        );
    }
}
