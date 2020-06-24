package ru.otus.spring.homework1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework1.service.IOService;

@ComponentScan
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        IOService ioService = context.getBean(IOService.class);
        ioService.start();
    }
}
