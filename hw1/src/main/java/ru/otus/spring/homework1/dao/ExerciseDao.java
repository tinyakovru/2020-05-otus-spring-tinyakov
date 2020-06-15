package ru.otus.spring.homework1.dao;

import ru.otus.spring.homework1.domain.Exercise;

import java.util.List;

public interface ExerciseDao {
    List<Exercise> getAllExercises();

    Exercise getExercise(int id);
}
