package godinho.savio.TvNews.controllers;

import godinho.savio.TvNews.Models.NewsPaper;
import godinho.savio.TvNews.Models.Person;
import godinho.savio.TvNews.dtos.PersonRecordDto;
import godinho.savio.TvNews.repositories.PersonRepository;
import godinho.savio.TvNews.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {


    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonService personService;

    @GetMapping("/people")
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    @PostMapping("/people")
    public ResponseEntity<Person> save(@RequestBody PersonRecordDto personDto) {
        Person person = personService.save(personDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(person); //aqui ele salvo no db e enviado

    }


}
