package sp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sp.rest.model.BookDto;

@Repository
public interface BooksRepository extends JpaRepository<BookDto, Long> {
}
