package sp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import sp.difexamples.ClientComponent;
import sp.difexamples.SingletonComponent;
import sp.difexamples.TransientComponent;

@SpringBootApplication
public class MySpringApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MySpringApplication.class, args);

        TransientComponent transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        SingletonComponent singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        ClientComponent c = context.getBean(ClientComponent.class);
        c.operation();
    }
}
