package godinho.savio.TvNews.repositories;

import godinho.savio.TvNews.Models.Mirror;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MirrorRepository extends JpaRepository<Mirror, UUID> {
}
