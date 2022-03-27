package com.hhwyz.newcompare;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    public List<String> show() throws IOException {
        Resource resource = new ClassPathResource("static/list2.txt");
        List<String> a = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                a.add(line.trim());
            }
        }
        return a;
    }
}
