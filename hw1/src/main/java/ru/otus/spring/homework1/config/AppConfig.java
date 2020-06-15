package ru.otus.spring.homework1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.homework1.dao.ExerciseDao;
import ru.otus.spring.homework1.dao.ExerciseDaoFromFile;
import ru.otus.spring.homework1.service.*;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

    @Bean
    ReadFileService readFileService(@Value("${resource.path}") String dataFile) {
        return new ReadFileServiceImpl(dataFile);
    }

    @Bean
    ExerciseDao exerciseDao(ReadFileService readFileService) {
        return new ExerciseDaoFromFile(readFileService);
    }

    @Bean
    ExerciseService exerciseService(ExerciseDao dao) {
        return new ExerciseServiceImpl(dao);
    }

    @Bean
    IOService ioService(ExerciseService exerciseService, @Value("${app.thresholdValue}") int thresholdValue) {
        return new IOServiceConsole(exerciseService, thresholdValue);
    }
}
