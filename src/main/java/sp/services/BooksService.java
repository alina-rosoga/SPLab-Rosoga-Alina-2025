package sp.services;

import org.springframework.stereotype.Service;
import sp.persistence.BooksRepository;
import sp.rest.model.BookDto;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository repository;

    public BooksService(BooksRepository repository) {
        this.repository = repository;
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
        return repository.save(dto);
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
