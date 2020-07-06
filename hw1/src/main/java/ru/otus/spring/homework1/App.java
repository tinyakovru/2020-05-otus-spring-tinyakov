package ru.otus.spring.homework1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.homework1.configs.YamlProperties;

@SpringBootApplication
@EnableConfigurationProperties(YamlProperties.class)
public class App {

    public static void main(String[] args) {
        var context = SpringApplication.run(App.class,args);
    }
}
