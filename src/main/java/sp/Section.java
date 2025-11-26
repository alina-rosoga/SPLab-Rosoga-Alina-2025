package sp;

import java.util.ArrayList;
import java.util.List;

public class Section extends Element {
    private final String title;
    private final List<Element> children = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void add(Element e) {
        if (e.getParent() != null) {
            throw new IllegalStateException("Element is already added to another section");
        }
        children.add(e);
        if (e instanceof Element) {
            e.setParent(this);
        }
    }

    @Override
    public void remove(Element e) {
        if (children.remove(e)) {
            e.setParent(null);
        }
    }

    @Override
    public Element getChild(int index) {
        return children.get(index);
    }

    @Override
    public void print() {
        System.out.println(title);
        for (Element e : children) {
            e.print();
        }
    }
}
