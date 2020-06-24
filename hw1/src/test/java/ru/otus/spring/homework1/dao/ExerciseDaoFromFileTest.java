package ru.otus.spring.homework1.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.spring.homework1.domain.Exercise;
import ru.otus.spring.homework1.exceptions.NoDataException;
import ru.otus.spring.homework1.service.ReadFileService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@DisplayName("класс ExerciseDaoFromFileTest")
class ExerciseDaoFromFileTest {

    private ExerciseDaoFromFile dao;

    @BeforeEach
    void setUp() throws NoDataException {
        ReadFileService mockReadFileService = mock(ReadFileService.class);
        List<String> lines = new ArrayList<String>(
                Arrays.asList("1;calculate: 2x2;result is 4;result is 5;result is 6;result is 7;1",
                        "2;calculate: 2x3;result is 2;result is 5;result is 6;result is 12;3",
                        "3;calculate: 2x4;result is 8;result is 4;result is 2;result is 1;1",
                        "4;calculate: 2+5;result is 3;result is 25;result is 12;result is 7;4",
                        "5;calculate: 2x6;result is 11;result is 12;result is 13;result is 14;2"));
        Mockito.when(mockReadFileService.readLines()).thenReturn(lines);
        dao = new ExerciseDaoFromFile(mockReadFileService);
    }

    @Test
    @DisplayName("должен парсить массив строк и возвращать список типа Exersize ")
    void shouldReturnAllExercises() {
        List<Exercise> exercises = dao.getAllExercises();
        assertAll("exercises",
                () -> assertEquals(5, exercises.size()),
                () -> assertEquals(4, exercises.get(3).getTrueAnswerNumber()));
    }

    @Test
    @DisplayName("должен возвращать инстанс типа Exercise по id")
    void shouldReturnExerciseById() {
        assertThat(dao.getExercise(2))
                .isNotNull()
                .asString()
                .startsWith("Exercise{question='calculate: 2x3', answerList=[result is 2,");
        assertThat(dao.getExercise(6))
                .isNull();
    }
}