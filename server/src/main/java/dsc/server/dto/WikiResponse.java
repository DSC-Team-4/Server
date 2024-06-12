package dsc.server.dto;

import dsc.server.entity.Wiki;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record WikiResponse(
        String title,
        String country,
        String uri,
        String editedAt
) {
    public static List<WikiResponse> ofList(List<Wiki> wikis) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        return wikis.stream().map(wiki -> new WikiResponse(
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getEditedAt().format(formatter))
        ).toList();
    }

    public static WikiResponse of(Wiki wiki) {
        return new WikiResponse(
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getEditedAt().toString()
        );
    }
}
