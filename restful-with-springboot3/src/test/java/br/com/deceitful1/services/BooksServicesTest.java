package br.com.deceitful1.services;

import br.com.deceitful1.dataDTO.version.v1.BooksDTO;
import br.com.deceitful1.mapper.ObjectMapper;
import br.com.deceitful1.models.Books;
import br.com.deceitful1.repositories.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BooksServicesTest
{
    @InjectMocks
    private BooksServices services;

    @Mock
    BooksRepository repository;

    List<BooksDTO> booksList = new ArrayList<>();
    Books books = new Books();

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.initMocks(this);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (int i = 1; i < 5; i++) {
            var entity = new BooksDTO((long) i, LocalDateTime.now().toLocalDate().atStartOfDay(), "Author" + i, i + 0.0, "title" + i);

            booksList.add(entity);

        }

    }

    @Test
    void findAll()
    {
    }

    @Test
    void findById()
    {

        var entity = ObjectMapper.parseObject(booksList.get(0), Books.class);
        entity.setId(1L);


        when(repository.findById(0L)).thenReturn(Optional.of(entity));

        BooksDTO result = services.findById(0L);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals("Author1", result.getAuthor());
        assertEquals("title1", result.getTitle());
        assertEquals(result.getLaunchDate(), LocalDateTime.now().toLocalDate().atStartOfDay());

        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getType().equals("GET") && link.getHref().contains("api/books")));
        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/books") && link.getType().equals("GET"))

        );
        assertTrue(result.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/books/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/books") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/books") && link.getType().equals("PUT"))

        );
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE") && link.getRel().value().equals("delete") && link.getHref().contains("api/books")
        ));


    }

    @Test
    void create()
    {
        var entity = ObjectMapper.parseObject(booksList.get(0), Books.class);
        var persisted = entity;
        persisted.setId(1L);

        BooksDTO dto = ObjectMapper.parseObject(booksList.get(0), BooksDTO.class);

        when(repository.save(entity)).thenReturn(persisted);


        var result = services.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());
        assertEquals("Author1", result.getAuthor());
        assertEquals("title1", result.getTitle());

        assertEquals(result.getLaunchDate(), LocalDateTime.now().toLocalDate().atStartOfDay());
        assertTrue(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self") && link.getHref().endsWith("/api/books") && link.getType().equals("POST")));


    }

    @Test
    void update()
    {
        var entity = ObjectMapper.parseObject(booksList.get(0), Books.class);

        var entity2 = entity;
        entity2.setId(1L);

        var persisted = booksList.get(0);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity2);

        var result = services.update(persisted);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());


        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("findAll") && link.getHref().endsWith("api/books") && link.getType().equals("GET"))

        );
        assertTrue(result.getLinks().stream()
                .anyMatch(link ->
                        link.getRel().value().equals("findById") &&
                                link.getHref().replaceAll("/$", "").matches(".*/api/books/\\d+$") &&
                                "GET".equalsIgnoreCase(link.getType())
                ));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("create") && link.getHref().endsWith("api/books") && link.getType().equals("POST")));

        assertTrue(result.getLinks().stream()
                .anyMatch(link -> link.getRel().value().equals("update") && link.getHref().endsWith("api/books") && link.getType().equals("PUT"))

        );
        assertTrue(result.getLinks().stream().anyMatch(link ->
                link.getType().equals("DELETE") && link.getRel().value().equals("delete") && link.getHref().contains("api/books")
        ));

    }

    @Test
    void delete()
    {
    }
}