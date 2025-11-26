package sp.command;

import sp.rest.model.BookDto;
import sp.services.BooksService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class UpdateBookCommand implements Command<BookDto> {
    private final BooksService booksService;
    private final Long id;
    private final BookDto dto;

    public UpdateBookCommand(BooksService booksService, Long id, BookDto dto) {
        this.booksService = booksService;
        this.id = id;
        this.dto = dto;
    }

    @Override
    public BookDto execute() {
        Optional<BookDto> updated = booksService.update(id, dto);
        return updated.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
}
