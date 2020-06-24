package ru.otus.spring.homework1.exceptions;

import java.io.FileNotFoundException;

public class NoDataException extends FileNotFoundException {
    public NoDataException(String s) {
        super(s);
    }
}
