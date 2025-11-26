package sp.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    public BookDto() {}

    public BookDto(Long id, String title, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors == null ? new ArrayList<>() : authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors == null ? new ArrayList<>() : authors;
    }

    // Backwards-compatible convenience methods for simple clients
    @Transient
    public String getAuthor() {
        return (authors != null && !authors.isEmpty()) ? authors.get(0).getName() : null;
    }

    @Transient
    public void setAuthor(String authorName) {
        if (authorName == null) return;
        this.authors.clear();
        this.authors.add(new Author(authorName));
    }
}
