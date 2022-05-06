package insw.service;

import insw.data.Person;
import insw.repository.PersonRepository;

import java.util.UUID;

public class PersonService {
    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person get(String id) {
        Person person = personRepository.selectById(id);
        if (person != null) {
            return person;
        }

        throw new IllegalArgumentException("Person not found");
    }

    public Person register(String name) {
        Person person = new Person(UUID.randomUUID().toString(), name);
        personRepository.insert(person);
        return person;
    }
}
