package dsc.server.dto;

import dsc.server.entity.NotEwmaWiki;
import java.util.List;

public record SearchResponse(
        Long id,
        String title,
        String country,
        String uri,
        String editedAt
) {

    public static List<SearchResponse>ofList(List<NotEwmaWiki> notEwmaWikis) {
        return notEwmaWikis.stream().map(wiki ->
                new SearchResponse(
                        wiki.getId(),
                        wiki.getTitle(),
                        wiki.getCountry(),
                        wiki.getUri(),
                        wiki.getEditedAt().toString()))
                .toList();
    }
}
