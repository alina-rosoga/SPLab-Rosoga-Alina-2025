package sp;

public abstract class Element {
    private Section parent = null;

    public Section getParent() {
        return parent;
    }

    protected void setParent(Section p) {
        this.parent = p;
    }

    public void add(Element e) {
        throw new UnsupportedOperationException("Cannot add to this element");
    }

    public void remove(Element e) {
        throw new UnsupportedOperationException("Cannot remove from this element");
    }

    public Element getChild(int index) {
        throw new UnsupportedOperationException("No children");
    }

    public abstract void print();
}
