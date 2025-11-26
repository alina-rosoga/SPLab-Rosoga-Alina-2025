package sp.services;

import sp.rest.model.BookDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BooksService {
    private final Map<Long, BookDto> store = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<BookDto> getAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<BookDto> getById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public BookDto create(BookDto dto) {
        Long id = idCounter.getAndIncrement();
        BookDto copy = new BookDto(id, dto.getTitle(), dto.getAuthor());
        store.put(id, copy);
        return copy;
    }

    public Optional<BookDto> update(Long id, BookDto dto) {
        if (!store.containsKey(id)) return Optional.empty();
        BookDto updated = new BookDto(id, dto.getTitle(), dto.getAuthor());
        store.put(id, updated);
        return Optional.of(updated);
    }

    public boolean delete(Long id) {
        return store.remove(id) != null;
    }
}
