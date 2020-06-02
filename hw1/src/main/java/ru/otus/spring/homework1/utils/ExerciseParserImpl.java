package ru.otus.spring.homework1.utils;

import ru.otus.spring.homework1.domain.ExerciseData;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExerciseParserImpl implements ExerciseParser {
    public ExerciseParserImpl(String resoursePath) {
        this.resoursePath = resoursePath;
    }

    private String resoursePath;

    public List<ExerciseData> parseResource() {
        List<ExerciseData> exerciseDataList = new ArrayList<>();
        Class cls = this.getClass();
        ClassLoader cLoader = cls.getClassLoader();
        URL url = cLoader.getResource(resoursePath);
        File file = new File(url.getPath());
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                exerciseDataList.add(new ExerciseData(
                        Integer.parseInt(line[0]),
                        line[1],
                        Arrays.copyOfRange(line, 2, line.length - 1),
                        Integer.parseInt(line[line.length - 1])));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return exerciseDataList;
    }
}
