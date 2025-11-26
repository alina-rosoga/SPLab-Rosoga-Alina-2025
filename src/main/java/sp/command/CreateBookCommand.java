package sp.command;

import sp.rest.model.BookDto;
import sp.services.BooksService;

public class CreateBookCommand implements Command<BookDto> {
    private final BooksService booksService;
    private final BookDto dto;

    public CreateBookCommand(BooksService booksService, BookDto dto) {
        this.booksService = booksService;
        this.dto = dto;
    }

    @Override
    public BookDto execute() {
        return booksService.create(dto);
    }
}
