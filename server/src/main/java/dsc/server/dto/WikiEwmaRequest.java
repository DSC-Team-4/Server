package dsc.server.dto;

import dsc.server.entity.NotEwmaWiki;
import java.time.LocalDateTime;
import java.util.List;

public record WikiEwmaRequest(
    Long metaId,
    Double beforeEwma,
    List<WikiInfo> wikiInfos
){
    public record WikiInfo(
            String title,
            String country,
            String uri,
            int editCount,
            LocalDateTime editedAt
    ) {
        public static WikiInfo of(NotEwmaWiki wiki, int editCount) {
            return new WikiInfo(
                    wiki.getTitle(),
                    wiki.getCountry(),
                    wiki.getUri(),
                    editCount,
                    wiki.getEditedAt()
            );
        }
    }
}
