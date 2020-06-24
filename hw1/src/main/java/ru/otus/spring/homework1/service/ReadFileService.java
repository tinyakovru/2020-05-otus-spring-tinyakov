package ru.otus.spring.homework1.service;

import ru.otus.spring.homework1.exceptions.NoDataException;

import java.util.List;

public interface ReadFileService {
    List<String> readLines() throws NoDataException;
}
