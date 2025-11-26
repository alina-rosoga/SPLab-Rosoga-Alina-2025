package sp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import sp.observer.AllBooksSubject;
import sp.observer.SseObserver;

@RestController
public class BooksSseController {
    private final AllBooksSubject allBooksSubject;

    public BooksSseController(AllBooksSubject allBooksSubject) {
        this.allBooksSubject = allBooksSubject;
    }

    @RequestMapping("/books-sse")
    public SseEmitter getBooksSse() {
        final SseEmitter emitter = new SseEmitter(0L);
        SseObserver observer = new SseObserver(emitter, allBooksSubject);
        allBooksSubject.attach(observer);
        return emitter;
    }
}
