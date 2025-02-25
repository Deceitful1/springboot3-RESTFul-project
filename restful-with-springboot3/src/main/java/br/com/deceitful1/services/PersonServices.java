package br.com.deceitful1.services;

import br.com.deceitful1.exceptions.ResourceNotFoundException;
import br.com.deceitful1.models.Person;
import br.com.deceitful1.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices
{
    @Autowired
    private PersonRepository personRepository;

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public Person findById(Long id)
    {
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
    }

    public List<Person> findAll()
    {
        logger.info("Finding all persons...");
        return personRepository.findAll();
    }

    public Person create(Person person)
    {
        logger.info("Creating one person...");


        return personRepository.save(person);
    }

    public Person update(Person person)
    {
        logger.info("Updating one person...");
        Person person1 = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setAddress(person.getAddress());
        person1.setGender(person.getGender());


        return personRepository.save(person1);
    }

    public void delete(Long id)
    {
        logger.info("Deleting one person...");
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(person);

    }



}
