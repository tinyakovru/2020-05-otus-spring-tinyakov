package ru.otus.spring.homework1.domain;

import java.util.List;

public class Exercise {
    private final int id;
    private final String question;
    private final List<String> answerList;
    private final int trueAnswerNumber;

    public int getTrueAnswerNumber() {
        return trueAnswerNumber;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public Exercise(int id, String question, List<String> answerList, int trueAnswerNumber) {
        this.id = id;
        this.question = question;
        this.answerList = answerList;
        this.trueAnswerNumber = trueAnswerNumber;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "question='" + question + '\'' +
                ", answerList=" + answerList +
                ", trueAnswerNumber=" + trueAnswerNumber +
                '}';
    }
}
