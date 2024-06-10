package dsc.server.service;

import dsc.server.entity.Wiki;
import dsc.server.repository.WikiRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WikiService {
    private final WikiRepository wikiRepository;

    public String save(Wiki wiki) {
        return wikiRepository.save(wiki).getId();
    }

    public List<Wiki> getAll() {
        return wikiRepository.findAll();
    }
}
