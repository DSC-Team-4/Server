package dsc.server.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data")
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
    private final LocalDateTime editedAt;

    public Wiki(String title, String country, String url, LocalDateTime editedAt) {
        this.title = title;
        this.country = country;
        this.uri = url;
        this.editedAt = editedAt;
    }
}
