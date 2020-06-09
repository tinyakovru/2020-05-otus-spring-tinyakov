package ru.otus.spring.homework1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.homework1.service.ExerciseService;

public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ExerciseService service = context.getBean(ExerciseService.class);
        service.getAllExercises().forEach(exercise -> System.out.println(exercise.toString()));
    }
}
