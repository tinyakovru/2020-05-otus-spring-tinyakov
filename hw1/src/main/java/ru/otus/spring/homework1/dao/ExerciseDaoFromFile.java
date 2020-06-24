package ru.otus.spring.homework1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.domain.Exercise;
import ru.otus.spring.homework1.domain.ExerciseData;
import ru.otus.spring.homework1.exceptions.NoDataException;
import ru.otus.spring.homework1.service.ReadFileService;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExerciseDaoFromFile implements ExerciseDao {
    private Map<Integer, Exercise> exerciseMap;
    private final ReadFileService readFileService;

    public ExerciseDaoFromFile(ReadFileService readFileService) {
        this.readFileService = readFileService;
    }

    public List<Exercise> getAllExercises() {
        return new ArrayList(getExerciseMap().values());
    }

    public Exercise getExercise(int id) {
        return getExerciseMap().get(id);
    }

    private void convertList2Map(List<ExerciseData> exerciseData) {
        exerciseMap = exerciseData
                .stream()
                .collect(Collectors.toMap(exd1 -> exd1.getId(),
                        exd2 -> new Exercise(exd2.getId(),
                                exd2.getQuestion(),
                                exd2.getAnswerList(),
                                exd2.getTrueAnswerNumber())));
    }

    private List<ExerciseData> parseExerciseData(List<String> inputLines) {
        List<ExerciseData> exerciseDataList = new ArrayList<>();
        inputLines.forEach(line -> {
            String[] lineSplited = line.split(";");
            exerciseDataList.add(new ExerciseData(
                    Integer.parseInt(lineSplited[0]),
                    lineSplited[1],
                    Arrays.copyOfRange(lineSplited, 2, lineSplited.length - 1),
                    Integer.parseInt(lineSplited[lineSplited.length - 1])));
        });
        return exerciseDataList;
    }

    private Map<Integer, Exercise> getExerciseMap() {
        if (exerciseMap == null) { //загружаем вопросы, если они еще не были загружены
            try {
                convertList2Map(parseExerciseData(readFileService.readLines()));
            } catch (NoDataException e) {
                e.printStackTrace();
            }
        }
        return exerciseMap;
    }

}
