package sp;

public class TableElement extends Element {
    private final String title;

    public TableElement(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Table: " + title);
    }
}
