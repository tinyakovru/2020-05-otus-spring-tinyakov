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
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.homework1.configs.YamlProperties;
import ru.otus.spring.homework1.exceptions.NoDataException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Класс ReadFileServiceImpl")
class ReadFileServiceImplTest {

    @Autowired
    YamlProperties props;

    @Autowired
    ReadFileServiceImpl readFileService;

    @DisplayName("должен считывать строки из файла с учетом локализации")
    @Test
    public void shouldReadLines() throws NoDataException {
        var stringArray = readFileService.readLines();
        assertEquals("1;calculate: 20x20;result is 4;result is 40;result is 400;result is 4000;3",stringArray.get(0));
        assertEquals(2,stringArray.size());
    }
}