package sp.observer;

import sp.rest.model.BookDto;

public interface Observer {
    void update(BookDto book);
}
