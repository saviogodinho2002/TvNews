package godinho.savio.TvNews.repositories;

import godinho.savio.TvNews.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository  extends JpaRepository<Role, UUID> {

    public Optional<Role> findByName(String name);

    @Query("SELECT R from Role R where R.name in :names")
    public Set<Role> findAllByName(Set<String> names);


}
