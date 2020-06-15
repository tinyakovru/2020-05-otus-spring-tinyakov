package ru.otus.spring.homework1.domain;

import java.util.Arrays;
import java.util.List;

public class ExerciseData {
    private final int id;
    private final String question;
    private final List<String> answerList;
    private final int trueAnswerNumber;

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public int getTrueAnswerNumber() {
        return trueAnswerNumber;
    }

    public ExerciseData(int id, String question, String[] answerArray, int trueAnswerNumber) {
        this.id = id;
        this.question = question;
        this.answerList = Arrays.asList(answerArray);
        this.trueAnswerNumber = trueAnswerNumber;
    }
}
