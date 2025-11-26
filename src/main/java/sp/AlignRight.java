package sp;

public class AlignRight implements AlignStrategy {
    private final int width;

    public AlignRight() {
        this.width = 60;
    }

    public AlignRight(int width) {
        this.width = width;
    }

    @Override
    public void render(Paragraph p) {
        String text = p.getText();
        int pad = Math.max(0, width - text.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pad; i++) sb.append(' ');
        sb.append(text);
        System.out.println("Paragraph (Right): " + sb.toString());
    }
}
