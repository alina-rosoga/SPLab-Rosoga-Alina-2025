package sp.command;

import sp.rest.model.BookDto;
import sp.services.BooksService;

import java.util.List;

public class GetAllBooksCommand implements Command<List<BookDto>> {
    private final BooksService booksService;

    public GetAllBooksCommand(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public List<BookDto> execute() {
        return booksService.getAll();
    }
}
