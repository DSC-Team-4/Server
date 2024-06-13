package dsc.server.global.util;

import dsc.server.entity.NotEwmaWiki;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class NotEwmaWikiListener extends AbstractMongoEventListener<NotEwmaWiki> {

    private final SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<NotEwmaWiki> event) {
        event.getSource().setId(generatorService.generateSequence(dsc.server.entity.NotEwmaWiki.SEQUENCE_NAME));
    }
}
