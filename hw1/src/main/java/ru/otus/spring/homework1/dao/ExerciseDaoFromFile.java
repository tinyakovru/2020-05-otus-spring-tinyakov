package ru.otus.spring.homework1.dao;

import org.jetbrains.annotations.NotNull;
import ru.otus.spring.homework1.domain.Exercise;
import ru.otus.spring.homework1.utils.ExerciseParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExerciseDaoFromFile implements ExerciseDao {
    private final ExerciseParser parser;
    private Map<Integer, Exercise> exerciseMap;

    public ExerciseDaoFromFile(@NotNull ExerciseParser parser) {
        this.parser = parser;
        loadFile();
    }

    private void loadFile(){
        exerciseMap = parser.parseResource()
                .stream()
                .collect(Collectors.toMap(exd1 -> exd1.getId(),
                        exd2 -> new Exercise(exd2.getId(), exd2.getQuestion(), exd2.getAnswerList(), exd2.getTrueAnswerNumber())));
    }

    public List<Exercise> getAllExercises() {
        return new ArrayList(exerciseMap.values());
    }

    public Exercise getExercise(int id) {
        return exerciseMap.get(id);
    }
}
