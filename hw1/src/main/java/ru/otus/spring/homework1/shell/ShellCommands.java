package ru.otus.spring.homework1.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.homework1.configs.YamlProperties;
import ru.otus.spring.homework1.service.IOService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    private String userName;
    private final IOService ioService;
    private final YamlProperties props;
    private final MessageSource messageSource;

    @ShellMethod(value = "login command", key = {"l", "login"})
    public String login(@ShellOption String userName) {
        if (userName.equals("")) {
            return messageSource.getMessage("input.name", null, props.getLocale());
        }
        this.userName = userName;
        return messageSource.getMessage("welcome.name", new String[]{userName}, props.getLocale());
    }

    @ShellMethod(value = "start", key = {"start", "s"})
    @ShellMethodAvailability(value = "isStartAvailable")
    public void start() {
        ioService.printResult(ioService.run(), userName);
    }

    private Availability isStartAvailable() {
        return (userName == null) ?
                Availability.unavailable(messageSource.getMessage("login", null, props.getLocale()))
                : Availability.available();
    }

}
