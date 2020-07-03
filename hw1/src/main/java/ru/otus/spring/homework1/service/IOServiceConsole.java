package ru.otus.spring.homework1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.configs.YamlProperties;
import ru.otus.spring.homework1.domain.Exercise;

import java.util.Scanner;

@PropertySource("classpath:application.properties")
@Service
public class IOServiceConsole implements IOService {

    private final ExerciseService exerciseService;
    private final Scanner scanner;
    private final int thresholdValue;
    private final YamlProperties props;
    private final AskService askService;
    private String userName;
    private final MessageSource messageSource;

    public IOServiceConsole(ExerciseService exerciseService,
                            YamlProperties props,
                            MessageSource messageSource,
                            AskService askService) {
        this.exerciseService = exerciseService;
        scanner = new Scanner(System.in);
        this.thresholdValue = props.getThresholdValue();
        this.props = props;
        this.messageSource = messageSource;
        this.askService = askService;
    }

    @Override
    public void start() {
        introduce();
        printResult(run());
    }

    private void introduce() {
        do {
//            System.out.println("Input your name please:");
            System.out.printf(messageSource.getMessage("input.name", null, props.getLocale()));
            userName = scanner.nextLine().trim();
        } while (userName.equals(""));
    }

    private int run() {
        int trueAnswers = 0;
        while (exerciseService.hasNext()) {
            Exercise exercise = exerciseService.getNextExercise();
            askService.ask(exercise);
            int answerNumber = scanner.nextInt();
            if (answerNumber == exercise.getTrueAnswerNumber()) {
                trueAnswers++;
            }
        }
        return trueAnswers;
    }

    private void printResult(int trueAnswers) {
        if (trueAnswers < thresholdValue) {
            System.out.printf(messageSource.getMessage("result.fail", new String[]{ userName,""+trueAnswers}, props.getLocale()));
        } else {
//            System.out.printf("%s! Your result is %d. Success!", userName, trueAnswers);
            System.out.printf(messageSource.getMessage("result.success", new String[]{ userName,""+trueAnswers}, props.getLocale()));
        }
    }
}
