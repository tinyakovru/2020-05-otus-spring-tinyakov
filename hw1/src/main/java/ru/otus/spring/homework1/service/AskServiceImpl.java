package ru.otus.spring.homework1.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.configs.YamlProperties;
import ru.otus.spring.homework1.domain.Exercise;

@Service
public class AskServiceImpl implements AskService {
    private final YamlProperties props;
    private MessageSource messageSource;
    private final String INSTRUCTION = "question.instruction";

    public AskServiceImpl(YamlProperties props, MessageSource messageSource) {
        this.props = props;
        this.messageSource = messageSource;
    }

    @Override
    public void ask(Exercise exercise) {
        System.out.println(exercise.getQuestion());
        System.out.println(messageSource.getMessage(INSTRUCTION, null, props.getLocale()));
        for (int i = 0; i < exercise.getAnswerList().size(); i++) {
            System.out.printf("%d. %s \n", i + 1, exercise.getAnswerList().get(i));
        }
    }
}
