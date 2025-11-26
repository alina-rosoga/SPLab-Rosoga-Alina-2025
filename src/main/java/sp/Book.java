package sp;

import java.util.ArrayList;
import java.util.List;

public class Book extends Section {
    private final List<Author> authors = new ArrayList<>();

    public Book(String title) {
        super(title);
    }

    public void addAuthor(Author a) {
        authors.add(a);
    }

    @Override
    public void print() {
        System.out.println("Book: " + getTitle());
        System.out.println("Authors:");
        for (Author a : authors) {
            a.print();
        }
        // print contents
        // call parent behavior: print children
        for (int i = 0;; i++) {
            try {
                Element e = getChild(i);
                e.print();
            } catch (IndexOutOfBoundsException ex) {
                break;
            }
        }
    }

    // helper to access title from Section (title is private)
    // use Section.getTitle() instead of reflection
}
