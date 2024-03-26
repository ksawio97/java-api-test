package com.example.testapi.controllers;

import com.example.testapi.entities.Person;
import com.example.testapi.entities.dto.PersonDTO;
import com.example.testapi.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    public PersonController(PersonService personService) { this.personService = personService; }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody PersonDTO dto) {
        Person person = Person.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        Person persistedPerson = personService.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(persistedPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@RequestBody long id) {
        Optional<Person> retrievedPerson = personService.findById(id);
        if (retrievedPerson.isPresent())
            return ResponseEntity.ok(retrievedPerson.get());

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }
}
