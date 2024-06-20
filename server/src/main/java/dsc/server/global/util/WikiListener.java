package dsc.server.global.util;

import dsc.server.entity.Wiki;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class WikiListener extends AbstractMongoEventListener<Wiki> {

    private final SequenceGeneratorService generatorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Wiki> event) {
        event.getSource().setId(generatorService.generateSequence(Wiki.SEQUENCE_NAME));
    }
}
