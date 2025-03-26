package br.com.deceitful1.services;

import br.com.deceitful1.controllers.PersonController;
import br.com.deceitful1.controllers.TestLogController;
import br.com.deceitful1.dataDTO.version.v1.PersonDTO;
import br.com.deceitful1.dataDTO.version.v2.PersonDTOV2;
import br.com.deceitful1.exceptions.RequiredObjectIsNullException;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel().withType("GET"));
        addHateoasLinks(personDTO);
        return personDTO;
    }

    public List<PersonDTO> findAll()
    {
        logger.info("Finding all persons...");
        var ent = ObjectMapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
        ent.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findAll()).withSelfRel().withType("GET")));
        ent.forEach(p -> addHateoasLinks(p));
        return ent;
    }

    public PersonDTO create(PersonDTO person)
    {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one person...");
        Person   person2 = ObjectMapper.parseObject(person, Person.class);

        var dto = ObjectMapper.parseObject(personRepository.save(person2), PersonDTO.class);

        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withSelfRel().withType("POST"));
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person)
    {
        logger.info("Creating one person...");

        var entity = personMapper.convertDTOtoEntity(person);
        return personMapper.convertEntityToDTO(personRepository.save(entity));
    }

    public PersonDTO update(PersonDTO person)
    {
        if (person == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one person...");
        Person person1 = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setAddress(person.getAddress());
        person1.setGender(person.getGender());

        var dto = ObjectMapper.parseObject(personRepository.save(person1), PersonDTO.class);

        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withSelfRel().withType("PUT"));
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id)
    {
        logger.info("Deleting one person...");
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found! "));
        personRepository.delete(person);

    }

    private static void addHateoasLinks(PersonDTO personDTO)
    {
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getId())).withRel("findById").withType("GET"));
        personDTO.add(linkTo(methodOn(PersonController.class).delete(personDTO.getId())).withRel("delete").withType("DELETE"));
        personDTO.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        personDTO.add(linkTo(methodOn(PersonController.class).create(personDTO)).withRel("create").withType("POST"));
        personDTO.add(linkTo(methodOn(PersonController.class).update(personDTO)).withRel("update").withType("PUT"));
    }


}
