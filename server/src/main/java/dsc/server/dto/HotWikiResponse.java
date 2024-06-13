package dsc.server.dto;

import dsc.server.entity.HotWiki;
import java.time.LocalDateTime;
import java.util.List;

public record HotWikiResponse(
        LocalDateTime startTime,
        LocalDateTime endTime,
        List<HotWikiInfo> wikiInfos
) {
    public record HotWikiInfo(
            String title,
            String country,
            String uri,
            int editCount,
            Double ewma,
            LocalDateTime editedAt
    ) {
        public static List<HotWikiInfo> ofList(List<HotWiki> hotWikis) {
            return hotWikis.stream().map(wiki -> new HotWikiInfo(
                    wiki.getTitle(),
                    wiki.getCountry(),
                    wiki.getUri(),
                    wiki.getEditCount(),
                    wiki.getEwma(),
                    wiki.getEditedAt()
            )).toList();
        }
    }
}
