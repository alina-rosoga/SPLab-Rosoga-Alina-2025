package sp.observer;

import sp.rest.model.BookDto;

public interface Subject {
    void attach(Observer o);
    void detach(Observer o);
    void notifyObservers(BookDto book);
}
