package com.example.issiteup;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/persons")
public class PersonsController {
    private final List<Person> persons = new ArrayList<>();

    public PersonsController() {
        persons.add(new Person("Andrei", 1));
        persons.add(new Person("Mihai", 2));
    }

    @GetMapping
    public List<Person> getPersons() {
        return persons;
    }

    @PostMapping
    public int createProduct(@RequestParam String name) {
        int id = 1 + persons.size();
        persons.add(new Person(name, id));
        return id;
    }

    @PostMapping(value = "/obj", consumes = "application/json")
    public ResponseEntity<String> createProduct(@RequestBody Person person) {
        persons.add(person);
        return new ResponseEntity<>("Person added successfully", HttpStatus.CREATED);
    }

    private Person findById(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestParam String name) {
        Person person = findById(id);
        if (person == null) {
            return new ResponseEntity<>("Person Not found", HttpStatus.NOT_FOUND);
        }
        person.setName(name);
        return new ResponseEntity<>("Person updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Person person = findById(id);
        if (person == null) {
            return new ResponseEntity<>("Person not found", HttpStatus.NOT_FOUND);
        }
        persons.remove(person);
        return new ResponseEntity<>("Person removed", HttpStatus.OK);
    }
}
