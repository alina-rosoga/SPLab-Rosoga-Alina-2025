package sp.observer;

import org.springframework.stereotype.Component;
import sp.rest.model.BookDto;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class AllBooksSubject implements Subject {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(BookDto book) {
        for (Observer o : observers) {
            try {
                o.update(book);
            } catch (Exception ex) {
                // ignore individual observer failures
            }
        }
    }

    public void add(BookDto book) {
        notifyObservers(book);
    }
}
