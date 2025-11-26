package sp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sp.rest.model.Author;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long> {
}
