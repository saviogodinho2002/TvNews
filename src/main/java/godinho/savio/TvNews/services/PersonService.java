package godinho.savio.TvNews.services;

import godinho.savio.TvNews.Models.Person;
import godinho.savio.TvNews.Models.Role;
import godinho.savio.TvNews.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }
    public Optional<Person> findById(UUID id) {
        return this.repository.findById(id);
    }
    public Person save(Person person) {
        return repository.save(person);
    }
    public Person assignRoleToPerson(Person person, Role role) {
        person.getRoles().add(role);
        return repository.save(person);
    }
    public Person syncRolesToPerson(Person person, List<Role> roles) {
        person.setRoles(new HashSet<Role>(roles));
        return repository.save(person);
    }
}
