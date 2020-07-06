package ru.otus.spring.homework1.service;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.configs.YamlProperties;
import ru.otus.spring.homework1.exceptions.NoDataException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@PropertySource("classpath:application.properties")
@Service
public class ReadFileServiceImpl implements ReadFileService {

    private final YamlProperties props;

    public ReadFileServiceImpl(YamlProperties props) {
        this.props = props;
    }

    private String getFilePath () {
        return props.getResourcePath().replace("__locale__","_"+props.getLocale().toString());
    }

    private InputStream getStream() {
        return this.getClass().getClassLoader().getResourceAsStream(getFilePath());
    }

    @Override
    public List<String> readLines() throws NoDataException {
        List<String> lines = new ArrayList<>();
        InputStream stream = getStream();

        if (stream == null) {
            throw new NoDataException("No data file: "+getFilePath());
        }

        Scanner scanner = new Scanner(getStream());
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }
}
