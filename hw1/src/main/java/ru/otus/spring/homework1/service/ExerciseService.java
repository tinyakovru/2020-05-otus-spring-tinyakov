package ru.otus.spring.homework1.service;

import ru.otus.spring.homework1.dao.ExerciseDao;
import ru.otus.spring.homework1.domain.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getAllExercises();
    Exercise getExercise(int id);
    Boolean hasNext();
    Exercise getNextExercise();
    void reset();
}
