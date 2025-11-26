package sp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sp.command.*;
import sp.rest.model.BookDto;
import sp.services.BooksService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final CommandExecutor executor;
    private final BooksService booksService;

    public BooksController(CommandExecutor executor, BooksService booksService) {
        this.executor = executor;
        this.booksService = booksService;
    }

    @GetMapping
    public List<BookDto> getAll() {
        return executor.execute(new GetAllBooksCommand(booksService));
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return executor.execute(new GetBookCommand(booksService, id));
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto) {
        BookDto created = executor.execute(new CreateBookCommand(booksService, dto));
        return ResponseEntity.created(URI.create("/books/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody BookDto dto) {
        return executor.execute(new UpdateBookCommand(booksService, id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        executor.execute(new DeleteBookCommand(booksService, id));
    }
}
