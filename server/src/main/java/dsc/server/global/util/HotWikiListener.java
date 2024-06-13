package dsc.server.global.util;

import dsc.server.entity.HotWiki;
import dsc.server.entity.Wiki;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HotWikiListener extends AbstractMongoEventListener<HotWiki> {

    private final SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<HotWiki> event) {
        event.getSource().setId(generatorService.generateSequence(HotWiki.SEQUENCE_NAME));
    }
}
