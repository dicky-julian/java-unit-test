package insw;

import insw.data.Person;
import insw.repository.PersonRepository;
import insw.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@Extensions({
        @ExtendWith(MockitoExtension.class)
})
public class MockTest {

    @Mock
    private PersonRepository personRepository;
    private PersonService personService;

    @BeforeEach
    void init() {
        personService = new PersonService(personRepository);
    }

    @Test
    void testGetPersonSuccess() {
        // provide object mock
        Mockito.when(personRepository.selectById("dicky"))
                .thenReturn(new Person("dicky", "Dicky"));

        Person person = personService.get("dicky");
        Assertions.assertNotNull(person);
        Assertions.assertEquals("dicky", person.getId());
        Assertions.assertEquals("Dicky", person.getName());
    }

    @Test
    void testNotFound() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personService.get("not found");
        });
    }

    @Test
    void testInsertSuccess() {
        Person person = personService.register("Dicky");
        Assertions.assertNotNull(person.getId());
        Assertions.assertEquals("Dicky", person.getName());

        // verify if method register call "insert() once"
        Mockito.verify(personRepository, Mockito.times(1))
                .insert(new Person(person.getId(), "Dicky"));
    }
}
