package ru.otus.spring.homework1.service;

import ru.otus.spring.homework1.dao.ExerciseDao;
import ru.otus.spring.homework1.domain.Exercise;

import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseDao exerciseDao;

    public ExerciseServiceImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseDao.getAllExercises();
    }

    @Override
    public Exercise getExercise(int id) {
        return exerciseDao.getExercise(id);
    }
}
