package dsc.server.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hot_wiki")
@Getter
@Setter
public class HotWiki {
    @Transient
    public static final String SEQUENCE_NAME = "hot_wiki_sequence";

    @Id
    private Long id;
    private final String title;
    private final String country;
    private final String uri;
    private final UUID metaId;
    private final int editCount;
    private final Double ewma;
    private final LocalDateTime editedAt;
    private LocalDateTime createdAt;

    public HotWiki(String title, String country, String uri, UUID metaId, Double ewma, int editCount, LocalDateTime editedAt, LocalDateTime createdAt) {
        this.title = title;
        this.country = country;
        this.uri = uri;
        this.metaId = metaId;
        this.ewma = ewma;
        this.editCount = editCount;
        this.editedAt = editedAt;
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(metaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof HotWiki wikiObj) {
            if (this.metaId != null && wikiObj.metaId != null) {
                return this.metaId.equals(wikiObj.metaId);
            }

            return false;
        }

        return false;
    }

    public static List<HotWiki> ofList(List<Wiki> wikis, LocalDateTime dbDate) {
        return wikis.stream().map(wiki -> new HotWiki(
                wiki.getTitle(),
                wiki.getCountry(),
                wiki.getUri(),
                wiki.getMetaId(),
                wiki.getEwma(),
                wiki.getEditCount(),
                wiki.getEditedAt(),
                dbDate
            )).toList();
    }
}
