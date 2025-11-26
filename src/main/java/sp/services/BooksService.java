package sp.services;

import org.springframework.stereotype.Service;
import sp.observer.AllBooksSubject;
import sp.persistence.CrudRepository;
import sp.rest.model.BookDto;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final CrudRepository<BookDto, Long> repository;
    private final AllBooksSubject allBooksSubject;

    public BooksService(CrudRepository<BookDto, Long> repository, AllBooksSubject allBooksSubject) {
        this.repository = repository;
        this.allBooksSubject = allBooksSubject;
    }

    public List<BookDto> getAll() {
        return repository.findAll();
    }

    public Optional<BookDto> getById(Long id) {
        return repository.findById(id);
    }

    public BookDto create(BookDto dto) {
        // ensure id is null so JPA will generate it
        dto.setId(null);
        BookDto saved = repository.save(dto);
        // notify SSE observers about the new book
        try {
            allBooksSubject.add(saved);
        } catch (Exception ex) {
            // ignore notification failures
        }
        return saved;
    }

    public Optional<BookDto> update(Long id, BookDto dto) {
        if (!repository.existsById(id)) return Optional.empty();
        dto.setId(id);
        return Optional.of(repository.save(dto));
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
