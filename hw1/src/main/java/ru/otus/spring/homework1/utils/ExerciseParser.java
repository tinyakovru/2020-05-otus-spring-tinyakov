package ru.otus.spring.homework1.utils;

import ru.otus.spring.homework1.domain.ExerciseData;

import java.io.FileNotFoundException;
import java.util.List;

public interface ExerciseParser {
    List<ExerciseData> parseResource();
}
