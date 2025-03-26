package godinho.savio.TvNews.services;

import godinho.savio.TvNews.Models.Role;
import godinho.savio.TvNews.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    RoleRepository repository;
    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public Role insert(Role role) {
        Role currentRole = this.getByName(role.getName());
        return Objects.requireNonNullElseGet(currentRole, () -> repository.save(role));
    }

    public Role getByName(String name) {
        Optional<Role> roleOp = repository.findByName(name);
        return roleOp.orElse(null);
    }
    public List<Role> findAll() {
        return this.repository.findAll();
    }
}
