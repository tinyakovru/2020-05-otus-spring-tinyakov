package ru.otus.spring.homework1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.exceptions.NoDataException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@PropertySource("classpath:application.properties")
@Service
public class ReadFileServiceImpl implements ReadFileService {

    private final String filePath;

    public ReadFileServiceImpl(@Value("${resource.path}") String filePath) {
        this.filePath = filePath;
    }

    private InputStream getStream() {
        return this.getClass().getClassLoader().getResourceAsStream(filePath);
    }

    @Override
    public List<String> readLines() throws NoDataException {
        List<String> lines = new ArrayList<>();
        InputStream stream = getStream();

        if (stream == null) {
            throw new NoDataException("No data file: "+filePath);
        }

        Scanner scanner = new Scanner(getStream());
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }
}
