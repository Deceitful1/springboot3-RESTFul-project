package br.com.deceitful1.repositories;

import br.com.deceitful1.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long>
{
}
