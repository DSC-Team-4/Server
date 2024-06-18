package dsc.server.entity;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data")
@Getter
@Setter
public class NotEwmaWiki {
    @Transient
    public static final String SEQUENCE_NAME = "not_wiki_sequence";

    @Id
    private Long id;
    private final String title;
    private final String country;
    private final String uri;
    private final Long metaId;
    private Double ewma = null;
    private final LocalDateTime editedAt;

    public NotEwmaWiki(String title, String country, String uri, Long metaId, LocalDateTime editedAt) {
        this.title = title;
        this.country = country;
        this.uri = uri;
        this.metaId = metaId;
        this.editedAt = editedAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(metaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NotEwmaWiki notEwmaWikiObj) {
            if (this.metaId != null && notEwmaWikiObj.metaId != null) {
                return this.metaId.equals(notEwmaWikiObj.metaId);
            }

            return false;
        }

        return false;
    }
}
