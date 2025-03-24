package godinho.savio.TvNews.repositories;

import godinho.savio.TvNews.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
