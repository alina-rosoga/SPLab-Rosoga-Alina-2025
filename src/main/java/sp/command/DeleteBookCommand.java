package sp.command;

import sp.services.BooksService;

public class DeleteBookCommand implements Command<Void> {
    private final BooksService booksService;
    private final Long id;

    public DeleteBookCommand(BooksService booksService, Long id) {
        this.booksService = booksService;
        this.id = id;
    }

    @Override
    public Void execute() {
        booksService.delete(id);
        return null;
    }
}
