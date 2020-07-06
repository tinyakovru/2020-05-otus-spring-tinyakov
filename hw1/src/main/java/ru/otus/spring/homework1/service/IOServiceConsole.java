package ru.otus.spring.homework1.service;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.configs.YamlProperties;
import ru.otus.spring.homework1.domain.Exercise;

@PropertySource("classpath:application.properties")
@Service
public class IOServiceConsole implements IOService {

    private final ExerciseService exerciseService;
    private final ResultScannerService scannerService;
    private final int thresholdValue;
    private final YamlProperties props;
    private final AskService askService;
    private final MessageSource messageSource;

    public IOServiceConsole(ExerciseService exerciseService,
                            ResultScannerService scannerService,
                            YamlProperties props,
                            MessageSource messageSource,
                            AskService askService) {
        this.exerciseService = exerciseService;
        this.scannerService = scannerService;
        this.thresholdValue = props.getThresholdValue();
        this.props = props;
        this.messageSource = messageSource;
        this.askService = askService;
    }

    public int run() {
        int trueAnswers = 0;
        while (exerciseService.hasNext()) {
            Exercise exercise = exerciseService.getNextExercise();
            askService.ask(exercise);
            int answerNumber = scannerService.nextInt();
            if (answerNumber == exercise.getTrueAnswerNumber()) {
                trueAnswers++;
            }
        }
        exerciseService.reset();
        return trueAnswers;
    }

    @Override
    public void printResult(int trueAnswers, String userName) {
        if (trueAnswers < thresholdValue) {
            System.out.printf(messageSource.getMessage("result.fail", new String[]{userName, "" + trueAnswers}, props.getLocale())+"\n");
        } else {
            System.out.printf(messageSource.getMessage("result.success", new String[]{userName, "" + trueAnswers}, props.getLocale())+"\n");
        }
    }
}
