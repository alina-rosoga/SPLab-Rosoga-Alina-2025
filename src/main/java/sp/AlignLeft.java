package sp;

public class AlignLeft implements AlignStrategy {
    private final int width;

    public AlignLeft() {
        this.width = 60;
    }

    public AlignLeft(int width) {
        this.width = width;
    }

    @Override
    public void render(Paragraph p) {
        String text = p.getText();
        // Left align: just print text
        System.out.println("Paragraph (Left): " + text);
    }
}
