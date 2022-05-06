package insw.repository;

import insw.data.Person;

public interface PersonRepository {

    Person selectById(String id);

    void insert(Person person);
}
