package sp.command;

import sp.rest.model.BookDto;
import sp.services.BooksService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class GetBookCommand implements Command<BookDto> {
    private final BooksService booksService;
    private final Long id;

    public GetBookCommand(BooksService booksService, Long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public BookDto execute() {
        return booksService.getById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
}
