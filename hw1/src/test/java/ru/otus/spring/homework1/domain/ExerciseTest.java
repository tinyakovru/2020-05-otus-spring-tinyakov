package ru.otus.spring.homework1.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Exercise")
class ExerciseTest {

    @DisplayName("корректно создается через конструктор и возвращает значения полей через геттеры")
    @Test
    public void shouldCorrectCreate(){
        Exercise exercise = new Exercise(3,"question3", Arrays.asList("answ1","answ2"),2);
        assertEquals(3,exercise.getId());
        assertEquals("question3",exercise.getQuestion());
        assertEquals("answ2",exercise.getAnswerList().get(1));
        assertEquals(2,exercise.getTrueAnswerNumber());
    }
}