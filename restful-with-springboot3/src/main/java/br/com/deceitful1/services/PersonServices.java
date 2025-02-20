package br.com.deceitful1.services;

import br.com.deceitful1.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices
{
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Person findById(String id)
    {
        logger.info("Finding by one person...");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel");
        person.setLastName("Lins");
        person.setAddress("rua 123");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll()
    {
        logger.info("Finding all persons...");
        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++) {

            Person person = mockPerson(i);
            persons.add(person);
        }


        return persons;

    }

    public Person create(Person person)
    {
        logger.info("Creating one person...");


        return person;
    }

    public Person update(Person person)
    {
        logger.info("Updating one person...");

        return person;
    }

    public void delete(String id)
    {
        logger.info("Deleting one person...");
    }



    private Person mockPerson(int i)
    {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person" + i);
        person.setLastName("Last name" + i);
        person.setAddress("somewhere in Brazil");
        person.setGender("Male");

        return person;
    }


}
