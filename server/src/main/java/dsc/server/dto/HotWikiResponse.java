package dsc.server.dto;

import dsc.server.entity.HotWiki;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public record HotWikiResponse(
        LocalDateTime startTime,
        LocalDateTime endTime,
        List<HotWikiInfo> wikiInfos
) {
    public static List<HotWikiResponse> ofList(Map<LocalDateTime, List<HotWiki>> hotWikiMap) {
        List<HotWikiResponse> result = new ArrayList<>();

        for (Entry<LocalDateTime, List<HotWiki>> entry : hotWikiMap.entrySet()) {
            List<HotWikiInfo> hotWikiInfos = HotWikiInfo.ofList(entry.getValue());
            LocalDateTime endTime = entry.getKey();
            LocalDateTime startTime = endTime.minusHours(3);

            HotWikiResponse hotWikiResponse = new HotWikiResponse(startTime, endTime, hotWikiInfos);
            result.add(hotWikiResponse);
        }

        return result;
    }


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
