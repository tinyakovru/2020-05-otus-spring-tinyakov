package ru.otus.spring.homework1.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Клаа ExerciseData")
class ExerciseDataTest {
    ExerciseData data;
    @BeforeEach
    void setUp() {
        data = new ExerciseData(3,"question3",new String[]{"ans1","ans2"},1);
    }

    @DisplayName("getId is Ok")
    @Test
    void getId() {
        assertEquals(3,data.getId());
    }

    @DisplayName("getQuestion is Ok")
    @Test
    void getQuestion() {
        assertEquals("question3",data.getQuestion());
    }

    @DisplayName("getAnswerList is Ok")
    @Test
    void getAnswerList() {
        assertEquals("ans1",data.getAnswerList().get(0));
    }

    @DisplayName("getTrueAnswerNumber is Ok")
    @Test
    void getTrueAnswerNumber() {
        assertEquals(1,data.getTrueAnswerNumber());
    }
}