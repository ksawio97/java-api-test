package com.example.testapi.services;

import com.example.testapi.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person save(Person person);

    Optional<Person> findById(Long id);

    List<Person> findAll();
}
