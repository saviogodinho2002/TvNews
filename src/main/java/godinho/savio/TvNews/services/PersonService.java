package godinho.savio.TvNews.services;

import godinho.savio.TvNews.Models.Person;
import godinho.savio.TvNews.Models.Role;
import godinho.savio.TvNews.dtos.PersonRecordDto;
import godinho.savio.TvNews.repositories.PersonRepository;
import godinho.savio.TvNews.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    PersonRepository personRepository;
    RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository repository, RoleRepository roleRepository) {
        this.personRepository = repository;
        this.roleRepository = roleRepository;
    }
    public Optional<Person> findById(UUID id) {
        return this.personRepository.findById(id);
    }
    @Transactional
    public Person save(PersonRecordDto personDto) {
        Person personModel = new Person();
        personModel.setName(personDto.name());
        personModel.setPhone(personDto.phone());
        personModel.setEmail(personDto.email());
        personModel.setRoles((this.roleRepository.findAllByName(personDto.roles())));

        return personRepository.save(personModel);
    }
    public Person assignRoleToPerson(Person person, Role role) {
        person.getRoles().add(role);
        return personRepository.save(person);
    }
    public Person syncRolesToPerson(Person person, List<Role> roles) {
        person.setRoles(new HashSet<Role>(roles));
        return personRepository.save(person);
    }
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }
}
