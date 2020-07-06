package ru.otus.spring.homework1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.homework1.dao.ExerciseDao;
import ru.otus.spring.homework1.domain.Exercise;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DisplayName("Класс ExerciseServiceImpl")
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class ExerciseServiceImplTest {

    @Configuration
    @Import(ExerciseServiceImpl.class)
    static class TestConf {
    }

    @MockBean
    private ExerciseDao exerciseDao;

    @Autowired
    private ExerciseServiceImpl exerciseService;

    @BeforeEach
    void init() {
        Exercise ex1 = new Exercise(1, "qw1", Arrays.asList("ans1", "ans2"), 2);
        Exercise ex2 = new Exercise(2, "qw2", Arrays.asList("ans3", "ans4"), 1);
        var testData = new ArrayList<Exercise>() {{
            add(ex1);
            add(ex2);
        }};
        given(exerciseDao.getAllExercises()).willReturn(testData);
    }

    @DisplayName("должен сообщать есть ли следующий вопрос")
    @Test
    void hasNextTest() {
        assertEquals(true, exerciseService.hasNext());
        exerciseService.getNextExercise();
        assertEquals(true, exerciseService.hasNext());
        exerciseService.getNextExercise();
        assertEquals(false, exerciseService.hasNext());
    }

    @DisplayName("Должен возвращать следующий вопрос")
    @Test
    @DirtiesContext
    void getNextExerciseTest() {
        var ex = exerciseService.getNextExercise();
        assertEquals(1, ex.getId());
        assertEquals("qw1", ex.getQuestion());
        ex = exerciseService.getNextExercise();
        assertEquals(1, ex.getTrueAnswerNumber());
        assertEquals("qw2", ex.getQuestion());
    }
}
