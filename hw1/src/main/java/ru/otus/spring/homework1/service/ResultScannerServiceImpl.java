package ru.otus.spring.homework1.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ResultScannerServiceImpl implements ResultScannerService {
    private final Scanner scanner;

    public ResultScannerServiceImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public int nextInt() {
        return scanner.nextInt();
    }
}
