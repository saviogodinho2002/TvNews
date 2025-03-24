package godinho.savio.TvNews.services;

import godinho.savio.TvNews.Models.NewsPaper;
import godinho.savio.TvNews.repositories.NewsPaperRepository;
import godinho.savio.TvNews.specifications.NewsPaperSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsPaperService {
    @Autowired
    private final NewsPaperRepository newsPaperRepository;

    public NewsPaperService(NewsPaperRepository newsPaperRepository) {
        this.newsPaperRepository = newsPaperRepository;
    }
    public List<NewsPaper> getByDescription(String description) {
        Specification<NewsPaper> spec = NewsPaperSpecification.filterByDescriptionIfNotEmpty(description);
        return newsPaperRepository.findAll(spec);
    }

    public NewsPaperRepository getNewsPaperRepository() {
        return newsPaperRepository;
    }
}
