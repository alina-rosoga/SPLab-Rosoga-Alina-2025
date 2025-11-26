package sp.persistence;

import org.springframework.stereotype.Component;
import sp.rest.model.BookDto;

import java.util.List;
import java.util.Optional;

@Component
public class BooksCrudAdapter implements CrudRepository<BookDto, Long> {
    private final BooksRepository booksRepository;

    public BooksCrudAdapter(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<BookDto> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<BookDto> findById(Long id) {
        return booksRepository.findById(id);
    }

    @Override
    public BookDto save(BookDto entity) {
        return booksRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        booksRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return booksRepository.existsById(id);
    }
}
