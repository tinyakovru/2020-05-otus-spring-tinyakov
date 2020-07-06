package ru.otus.spring.homework1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.MessageSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.homework1.configs.YamlProperties;
import ru.otus.spring.homework1.domain.Exercise;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Класс IOServiceConsole")
class IOServiceConsoleTest {

    @Autowired
    ExerciseService exerciseService;

    @MockBean
    ResultScannerService scannerService;

    @Autowired
    YamlProperties props;

    @MockBean
    MessageSource messageSource;

    @MockBean
    AskService askService;

    @Autowired
    IOService ioService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldPrintExercises() {
        Exercise exercise1 = new Exercise(1, "calculate: 20x20", Arrays.asList("result is 4", "result is 40", "result is 400", "result is 4000"), 3);
        Exercise exercise2 = new Exercise(2, "calculate: 2x3", Arrays.asList("result is 2", "result is 5", "result is 6", "result is 12"), 2);
        List<Exercise> exerciseList = Arrays.asList(exercise1, exercise2);

        given(scannerService.nextInt()).willReturn(2);
        int result = ioService.run();

        assertEquals(1, result);
        verify(askService, times(2)).ask(any(Exercise.class));
    }
}