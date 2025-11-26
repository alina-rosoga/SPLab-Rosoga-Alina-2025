package sp;

public class AlignCenter implements AlignStrategy {
    private final int width;

    public AlignCenter() {
        this.width = 60;
    }

    public AlignCenter(int width) {
        this.width = width;
    }

    @Override
    public void render(Paragraph p) {
        String text = p.getText();
        int totalPad = Math.max(0, width - text.length());
        int left = totalPad / 2;
        int right = totalPad - left;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < left; i++) sb.append(' ');
        sb.append(text);
        for (int i = 0; i < right; i++) sb.append(' ');
        System.out.println("Paragraph (Center): " + sb.toString());
    }
}
