package ru.otus.spring.homework1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework1.dao.ExerciseDao;
import ru.otus.spring.homework1.domain.Exercise;

import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseDao exerciseDao;
    private int currentExercise;

    @Autowired
    public ExerciseServiceImpl(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
        currentExercise = 0;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseDao.getAllExercises();
    }

    @Override
    public Exercise getExercise(int id) {
        return exerciseDao.getExercise(id);
    }

    @Override
    public Boolean hasNext() {
        return currentExercise < exerciseDao.getAllExercises().size();
    }

    @Override
    public Exercise getNextExercise() {
        return exerciseDao.getAllExercises().get(currentExercise++);
    }

    @Override
    public void reset() {
        currentExercise = 0;
    }
}
