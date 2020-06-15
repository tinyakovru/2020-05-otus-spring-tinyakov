package ru.otus.spring.homework1.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

public class ReadFileServiceImpl implements ReadFileService {

    private final String filePath;

    public ReadFileServiceImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InputStream stream() {
        Class cls = this.getClass();
        ClassLoader cLoader = cls.getClassLoader();
        URL url = cLoader.getResource(filePath);
        System.out.println("url.getPath()=" + url.getPath());
        File file = new File(url.getPath());

        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
