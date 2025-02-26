package br.com.deceitful1.services;

import br.com.deceitful1.controllers.TestLogController;
import br.com.deceitful1.dataDTO.version.v1.PersonDTO;
import br.com.deceitful1.dataDTO.version.v2.PersonDTOV2;
import br.com.deceitful1.exceptions.ResourceNotFoundException;
import br.com.deceitful1.mapper.ObjectMapper;
import br.com.deceitful1.mapper.custom.PersonMapper;
import br.com.deceitful1.models.Person;
import br.com.deceitful1.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices
{
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    public PersonDTO findById(Long id)
    {
        PersonDTO personDTO = ObjectMapper.parseObject(personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found!!")), PersonDTO.class);

        return personDTO;
    }

    public List<PersonDTO> findAll()
    {
        logger.info("Finding all persons...");
        return ObjectMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person)
    {
        logger.info("Creating one person...");
        Person person2 = ObjectMapper.parseObject(person, Person.class);

        return ObjectMapper.parseObject(personRepository.save(person2), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person)
    {
        logger.info("Creating one person...");

        var entity = personMapper.convertDTOtoEntity(person);
        return personMapper.convertEntityToDTO(personRepository.save(entity));
    }

    public PersonDTO update(PersonDTO person)
    {
        logger.info("Updating one person...");
        Person person1 = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setAddress(person.getAddress());
        person1.setGender(person.getGender());


        return ObjectMapper.parseObject(personRepository.save(person1), PersonDTO.class);
    }

    public void delete(Long id)
    {
        logger.info("Deleting one person...");
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found! "));
        personRepository.delete(person);

    }


}
