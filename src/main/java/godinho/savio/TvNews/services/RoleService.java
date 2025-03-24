package godinho.savio.TvNews.services;

import godinho.savio.TvNews.Models.Role;
import godinho.savio.TvNews.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RoleService {

    RoleRepository repository;
    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }
    public Role insert(Role role) {
        Role currentRole = this.getByName(role.getName());
        return Objects.requireNonNullElseGet(currentRole, () -> repository.save(role));
    }

    public Role getByName(String name) {
        Optional<Role> roleOp = repository.findByName(name);
        return roleOp.orElse(null);
    }
}
