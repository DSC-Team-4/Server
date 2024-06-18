package dsc.server.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "wiki")
@Getter
@Setter
public class Wiki {
    @Transient
    public static final String SEQUENCE_NAME = "wiki_sequence";

    @Id
    private Long id;
    private final String title;
    private final String country;
    private final String uri;
    private final Long metaId;
    private final int editCount;
    private Double ewma;
    private final LocalDateTime editedAt;

    public Wiki(String title, String country, String uri, Long metaId, Double ewma, int editCount, LocalDateTime editedAt) {
        this.title = title;
        this.country = country;
        this.uri = uri;
        this.metaId = metaId;
        this.ewma = ewma;
        this.editCount = editCount;
        this.editedAt = editedAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(metaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Wiki wikiObj) {
            if (this.metaId != null && wikiObj.metaId != null) {
                return this.metaId.equals(wikiObj.metaId);
            }

            return false;
        }

        return false;
    }

    public void updateEwma(Double ewma) {
        this.ewma = ewma;
    }
}
