package sp.observer;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import sp.rest.model.BookDto;

import java.io.IOException;

public class SseObserver implements Observer {
    private final SseEmitter emitter;
    private final Subject subject;

    public SseObserver(SseEmitter emitter, Subject subject) {
        this.emitter = emitter;
        this.subject = subject;
        // detach when emitter completes or times out
        this.emitter.onCompletion(() -> subject.detach(this));
        this.emitter.onTimeout(() -> subject.detach(this));
        this.emitter.onError((ex) -> subject.detach(this));
    }

    @Override
    public void update(BookDto book) {
        try {
            emitter.send(book, MediaType.APPLICATION_JSON);
        } catch (IOException e) {
            try {
                emitter.completeWithError(e);
            } finally {
                subject.detach(this);
            }
        }
    }
}
