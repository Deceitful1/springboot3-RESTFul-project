package br.com.deceitful1.services;

import br.com.deceitful1.dataDTO.version.v1.PersonDTO;
import br.com.deceitful1.exceptions.RequiredObjectIsNullException;
import br.com.deceitful1.models.Person;
import br.com.deceitful1.repositories.PersonRepository;
import br.com.deceitful1.unittests.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest
{
    MockPerson input;

    @InjectMocks
    private PersonServices service;

    @Mock
    PersonRepository repository;


    @BeforeEach
    void setUp()
    {
        input = new MockPerson();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById()
    {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        var result = service.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("self") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/person") && link.getType().equals("GET"))

        );
        assertTrue(result.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/person") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/person") && link.getType().equals("PUT"))

        );
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE")&&link.getRel().value().equals("delete")&&link.getHref().contains("api/person/")
        ));


        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void create()
    {

        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")&&link.getType().equals("POST")&&link.getHref().endsWith("api/person")));
        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/person") && link.getType().equals("GET"))

        );
        assertTrue(result.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/person") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/person") && link.getType().equals("PUT"))

        );
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE")&&link.getRel().value().equals("delete")&&link.getHref().contains("api/person/")
        ));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());


    }

    @Test
    void testCreateWithNullException()
    {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () ->
        {
            service.create(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update()
    {
        Person person = input.mockEntity(1);
        Person persisted = person;
        persisted.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")&&link.getType().equals("PUT")&&link.getHref().endsWith("api/person")));
        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/person") && link.getType().equals("GET"))

        );
        assertTrue(result.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/person") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/person") && link.getType().equals("PUT"))

        );
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE")&&link.getRel().value().equals("delete")&&link.getHref().contains("api/person/")
        ));


        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());


    }

    @Test
    void testUpdateWithNullException()
    {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () ->
        {
            service.update(null);
        });

        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete()
    {
        Person person = input.mockEntity(1);
        person.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        service.delete(1L);
        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).delete(any(Person.class));
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAll()
    {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> dtos = service.findAll();

        assertNotNull(dtos);

        var personOne = dtos.get(1);


        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        assertTrue(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("api/person") && link.getType().equals("GET")));

        assertTrue(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/person") && link.getType().equals("GET"))

        );
        assertTrue(personOne.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/person") && link.getType().equals("POST")));

        assertTrue(personOne.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/person") && link.getType().equals("PUT"))

        );
        assertTrue(personOne.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE")&&link.getRel().value().equals("delete")&&link.getHref().contains("api/person/")
        ));



        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personTwo = dtos.get(2);

        assertNotNull(personTwo);
        assertNotNull(personTwo.getId());
        assertNotNull(personTwo.getLinks());

        assertTrue(personTwo.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("api/person") && link.getType().equals("GET")));

        assertTrue(personTwo.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/person") && link.getType().equals("GET"))

        );
        assertTrue(personTwo.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(personTwo.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/person") && link.getType().equals("POST")));

        assertTrue(personTwo.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/person") && link.getType().equals("PUT"))

        );
        assertTrue(personTwo.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE")&&link.getRel().value().equals("delete")&&link.getHref().contains("api/person")
        ));



        assertEquals("Address Test2", personTwo.getAddress());
        assertEquals("First Name Test2", personTwo.getFirstName());
        assertEquals("Last Name Test2", personTwo.getLastName());
        assertEquals("Male", personTwo.getGender());

        var personThree = dtos.get(3);

        assertNotNull(personThree);
        assertNotNull(personThree.getId());
        assertNotNull(personThree.getLinks());

        assertTrue(personThree.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("api/person") && link.getType().equals("GET")));

        assertTrue(personThree.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/person") && link.getType().equals("GET"))

        );
        assertTrue(personThree.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(personThree.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/person") && link.getType().equals("POST")));

        assertTrue(personThree.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/person") && link.getType().equals("PUT"))

        );
        assertTrue(personThree.getLinks().stream().anyMatch(link ->
            link.getType().equals("DELETE")&&link.getRel().value().equals("delete")&&link.getHref().contains("api/person")
        ));


        assertEquals("Address Test3", personThree.getAddress());
        assertEquals("First Name Test3", personThree.getFirstName());
        assertEquals("Last Name Test3", personThree.getLastName());
        assertEquals("Female", personThree.getGender());

        var personFour = dtos.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getId());
        assertNotNull(personFour.getLinks());


        assertTrue(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("api/person") && link.getType().equals("GET")));

        assertTrue(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/person") && link.getType().equals("GET"))

        );
        assertTrue(personFour.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/person/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/person") && link.getType().equals("POST")));

        assertTrue(personFour.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/person") && link.getType().equals("PUT"))

        );
        assertTrue(personFour.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE")&&link.getRel().value().equals("delete")&&link.getHref().contains("api/person")
        ));


        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());



        /*
        List<Person> list = new ArrayList<>();

        var entity = input.mockEntity(1);
        entity.setId(1L);
        list.add(entity);

        var entity2 = input.mockEntity(2);
        entity2.setId(2L);
        list.add(entity2);

        var entity3 = input.mockEntity(3);
        entity3.setId(3L);
        list.add(entity3);



        assertNotNull(result.getLinks().stream()
                .anyMatch(link -> link.getRel().equals("self")&& link.getHref().endsWith("api/person/1") && link.getType().equals("GET"))

        );

         */
    }
}