package ru.otus.spring.homework1.dao;

import ru.otus.spring.homework1.domain.Exercise;
import ru.otus.spring.homework1.domain.ExerciseData;
import ru.otus.spring.homework1.service.ReadFileService;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class ExerciseDaoFromFile implements ExerciseDao {
    private Map<Integer, Exercise> exerciseMap;
    private List<ExerciseData> exerciseDataList;
    private final ReadFileService readFileService;

    public ExerciseDaoFromFile(ReadFileService readFileService) {
        this.readFileService = readFileService;
        exerciseDataList = parseResource(readFileService.stream());
        convertList2Map(exerciseDataList);
    }

    public List<Exercise> getAllExercises() {
        return new ArrayList(exerciseMap.values());
    }

    public Exercise getExercise(int id) {
        return exerciseMap.get(id);
    }

    private void convertList2Map(List<ExerciseData> exerciseData) {
        exerciseMap = exerciseData
                .stream()
                .collect(Collectors.toMap(exd1 -> exd1.getId(),
                        exd2 -> new Exercise(exd2.getId(), exd2.getQuestion(), exd2.getAnswerList(), exd2.getTrueAnswerNumber())));
    }

    private List<ExerciseData> parseResource(InputStream inputStream) {
        List<ExerciseData> exerciseDataList = new ArrayList<>();

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(";");
            exerciseDataList.add(new ExerciseData(
                    Integer.parseInt(line[0]),
                    line[1],
                    Arrays.copyOfRange(line, 2, line.length - 1),
                    Integer.parseInt(line[line.length - 1])));
        }
        return exerciseDataList;
    }

}
