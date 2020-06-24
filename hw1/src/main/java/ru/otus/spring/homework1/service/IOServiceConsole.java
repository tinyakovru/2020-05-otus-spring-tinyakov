package ru.otus.spring.homework1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.domain.Exercise;

import java.util.Scanner;

@PropertySource("classpath:application.properties")
@Service
public class IOServiceConsole implements IOService {

    private final ExerciseService exerciseService;
    private final Scanner scanner;
    private final int thresholdValue;
    private String userName;

    public IOServiceConsole(ExerciseService exerciseService, @Value("${app.thresholdValue}") int thresholdValue) {
        this.exerciseService = exerciseService;
        scanner = new Scanner(System.in);
        this.thresholdValue = thresholdValue;
    }

    @Override
    public void start() {
        introduce();
        printResult(run());
    }

    private void introduce() {
        do {
            System.out.println("Input your name please:");
            userName = scanner.nextLine().trim();
        } while (userName.equals(""));
    }

    private int run() {
        int trueAnswers = 0;
        while (exerciseService.hasNext()) {
            Exercise exercise = exerciseService.getNextExercise();
            exercise.ask();
            int answerNumber = scanner.nextInt();
            if (answerNumber == exercise.getTrueAnswerNumber()) {
                trueAnswers++;
            }
        }
        return trueAnswers;
    }

    private void printResult(int trueAnswers) {
        if (trueAnswers < thresholdValue) {
            System.out.printf("%s! Your result is %d. It's fail, bro!", userName, trueAnswers);
        } else {
            System.out.printf("%s! Your result is %d. Success!", userName, trueAnswers);
        }
    }
}
