package com.hhwyz.newcompare;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Resource resource = new ClassPathResource("static/list.txt");
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
        System.out.println(a.size());
        Set<String> b = new HashSet<>(a);
        System.out.println(b.size());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\zihao_wang\\Downloads\\newcompare\\src\\main\\resources\\static\\list2.txt"))) {
            for (String s : b) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        }
    }
}
