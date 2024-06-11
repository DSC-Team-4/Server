package dsc.server.dto;

import dsc.server.entity.Wiki;
import java.util.List;
import lombok.AllArgsConstructor;

public record SearchResponse(
        String title,
     String country,
     String url,
     String editedAt
) {

    public static List<SearchResponse>ofList(List<Wiki> wikis) {
        return wikis.stream().map(wiki ->
                new SearchResponse(
                        wiki.getTitle(),
                        wiki.getCountry(),
                        wiki.getUrl(),
                        wiki.getEditedAt().toString()))
                .toList();
    }
}
