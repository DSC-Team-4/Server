package dsc.server.dto;

import dsc.server.entity.NotEwmaWiki;
import dsc.server.entity.Wiki;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record WikiEwmaRequest(
    UUID metaId,
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
