package godinho.savio.TvNews.services;

import godinho.savio.TvNews.Models.NewsPaper;
import godinho.savio.TvNews.repositories.NewsPaperRepository;
import godinho.savio.TvNews.specifications.NewsPaperSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class NewsPaperService {
    private final NewsPaperRepository newsPaperRepository;

    public NewsPaperService(NewsPaperRepository newsPaperRepository) {
        this.newsPaperRepository = newsPaperRepository;
    }

    public List<NewsPaper> getByDescription(String description) {
        Specification<NewsPaper> spec = NewsPaperSpecification.filterByDescription(description);
        return newsPaperRepository.findAll(spec);
    }

    public NewsPaperRepository getNewsPaperRepository() {
        return newsPaperRepository;
    }
}
