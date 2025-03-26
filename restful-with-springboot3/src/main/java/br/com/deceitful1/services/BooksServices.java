package br.com.deceitful1.services;

import br.com.deceitful1.controllers.BooksController;
import br.com.deceitful1.dataDTO.version.v1.BooksDTO;
import br.com.deceitful1.exceptions.ResourceNotFoundException;
import br.com.deceitful1.mapper.ObjectMapper;
import br.com.deceitful1.models.Books;
import br.com.deceitful1.repositories.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksServices
{

    Logger logger = LoggerFactory.getLogger(BooksServices.class);

    @Autowired
    private BooksRepository repository;

    public List<BooksDTO> findAll()
    {
        logger.info("Finding all books");
        List<Books> books = repository.findAll();
        var entity = ObjectMapper.parseListObjects(books, BooksDTO.class);

        for (var ent : entity) {
            ent.add(linkTo(methodOn(BooksController.class).findAll()).withSelfRel().withType("GET"));
            addHateoasLinks(ent);
        }
        return entity;
    }


    public BooksDTO findById(Long id)
    {
        logger.info("Finding a book by id {}", id);
        Books books = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        var entity = ObjectMapper.parseObject(books, BooksDTO.class);

        entity.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel().withType("GET"));
        addHateoasLinks(entity);

        return entity;
    }

    public BooksDTO create(BooksDTO book)
    {
        logger.info("Creating a book...");
        var entity = ObjectMapper.parseObject(book, Books.class);

        var entity2 = ObjectMapper.parseObject(repository.save(entity), BooksDTO.class);

        entity2.add(linkTo(methodOn(BooksController.class).create(entity2)).withSelfRel().withType("POST"));
        addHateoasLinks(entity2);
        return entity2;
    }

    public BooksDTO update(BooksDTO book)
    {
        logger.info("Updating a book...");
        Books entity = repository.findById(book.getId()).get();

        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setTitle(book.getTitle());

        var result = ObjectMapper.parseObject(repository.save(entity), BooksDTO.class);

        result.add(linkTo(methodOn(BooksController.class).update(book)).withSelfRel().withType("PUT"));

        addHateoasLinks(result);
        return result;
    }

    public void delete(Long id)
    {
        logger.info("Deleting a book...");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        repository.deleteById(id);
    }

    private static void addHateoasLinks(BooksDTO books)
    {
        books.add(linkTo(methodOn(BooksController.class).findById(books.getId())).withRel("findById").withType("GET"));
        books.add(linkTo(methodOn(BooksController.class).findAll()).withRel("findAll").withType("GET"));
        books.add(linkTo(methodOn(BooksController.class).delete(books.getId())).withRel("delete").withType("DELETE"));
        books.add(linkTo(methodOn(BooksController.class).update(books)).withRel("update").withType("PUT"));
        books.add(linkTo(methodOn(BooksController.class).create(books)).withRel("create").withType("POST"));
    }


}
