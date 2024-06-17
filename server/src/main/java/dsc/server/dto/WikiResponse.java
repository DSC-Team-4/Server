package dsc.server.dto;

import dsc.server.entity.NotEwmaWiki;
import dsc.server.entity.Wiki;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public record WikiResponse(
        Long id,
        String title,
        String country,
        String uri,
        UUID metaId,
        Double ewma,
        int editCount,
        String editedAt
) {
    public static List<WikiResponse> ofList(List<Wiki> wikis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        return wikis.stream().map(wiki -> new WikiResponse(
                wiki.getId(),
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getMetaId(),
                wiki.getEwma(),
                wiki.getEditCount(),
                wiki.getEditedAt().format(formatter))
        ).toList();
    }

    public static List<WikiResponse> ofList(List<Wiki> wikis, int maxCount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        return wikis.stream().map(wiki -> new WikiResponse(
                wiki.getId(),
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getMetaId(),
                wiki.getEwma(),
                wiki.getEditCount(),
                wiki.getEditedAt().format(formatter))
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
                wiki.getEditedAt().toString()
        );
    }
}
