package sp;

public class Author {
    private final String name;

    public Author(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("Author: " + name);
    }
}
