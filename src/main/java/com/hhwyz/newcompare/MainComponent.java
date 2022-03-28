package com.hhwyz.newcompare;

import com.alibaba.fastjson.JSON;
import com.hhwyz.newcompare.dto.GetDTO;
import com.hhwyz.newcompare.dto.SetParam;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class MainComponent {
    static List<List<String>> lilyList = new ArrayList<>();

    static {
        Resource resource = new ClassPathResource("static/list2.txt");
        Set<String> a = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                a.add(line.trim());
            }
            List<String> c = new ArrayList<>(a);
            Collections.shuffle(c);
            lilyList.add(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GetDTO get() {
        int size = lilyList.size();
        for (int i = 0; i < size; i++) {
            if (lilyList.get(i).size() > 10) {
                GetDTO getDTO = new GetDTO();
                getDTO.setIndex(i);
                getDTO.setThisList(lilyList.get(i));
                return getDTO;
            }
        }
        return null;
    }

    public void set(SetParam setParam) {
        System.out.println(JSON.toJSONString(setParam));
        List<String> splitList = lilyList.get(setParam.getIndex());
        List<String> rightList = new ArrayList<>();
        for (Integer biggerIndex : setParam.getBiggerIndex()) {
            rightList.add(splitList.get(biggerIndex));
        }
        splitList.removeAll(rightList);
        lilyList.add(setParam.getIndex() + 1, rightList);
    }

    public List<List<String>> download() {
        return lilyList;
    }
}
