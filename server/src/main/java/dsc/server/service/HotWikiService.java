package dsc.server.service;

import dsc.server.dto.HotWikiResponse;
import dsc.server.dto.HotWikiResponse.HotWikiInfo;
import dsc.server.entity.HotWiki;
import dsc.server.repository.HotWikiRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotWikiService {
    private final HotWikiRepository hotWikiRepository;

    public List<HotWikiResponse> getAllHotWikis() {
        List<HotWiki> hotWikis = hotWikiRepository.findAll();
        Map<LocalDateTime, List<HotWiki>> hotWikisBySavedTime = hotWikis.stream()
                .collect(Collectors.groupingBy(HotWiki::getCreatedAt));

        List<HotWikiResponse> result = new ArrayList<>();

        for (Entry<LocalDateTime, List<HotWiki>> entry : hotWikisBySavedTime.entrySet()) {
            List<HotWikiInfo> hotWikiInfos = HotWikiInfo.ofList(entry.getValue());
            LocalDateTime endTime = entry.getKey();
            LocalDateTime startTime = endTime.minusHours(3);

            HotWikiResponse hotWikiResponse = new HotWikiResponse(startTime, endTime, hotWikiInfos);
            result.add(hotWikiResponse);
        }

        return result;
    }

}
