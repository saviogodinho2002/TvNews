package godinho.savio.TvNews.specifications;

import godinho.savio.TvNews.Models.NewsPaper;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class NewsPaperSpecification {

    public static Specification<NewsPaper> filterByDescriptionIfNotEmpty(String description) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();



            if (description != null && !description.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("description")),
                        "%" + description.toLowerCase() + "%"
                ));
            }

            // Ordena os resultados pela data de criação mais recente
            query.orderBy(criteriaBuilder.desc(root.get("createdAt")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}
