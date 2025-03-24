package godinho.savio.TvNews.repositories;

import godinho.savio.TvNews.Models.NewsPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NewsPaperRepository extends JpaRepository<NewsPaper, UUID>, JpaSpecificationExecutor<NewsPaper> {

    @Query("SELECT np FROM NewsPaper np WHERE (:description IS NULL OR LOWER(np.description) LIKE LOWER(CONCAT('%', :description, '%'))) AND np.description IS NOT NULL")
    public List<NewsPaper> getAndSearchByDescriptionIfNotNull(@Param("description") String description);



}
