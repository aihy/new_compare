package com.hhwyz.newcompare.service;

import com.hhwyz.newcompare.MainComponent;
import com.hhwyz.newcompare.dto.GetDTO;
import com.hhwyz.newcompare.dto.SetParam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MainService {
    @javax.annotation.Resource
    private MainComponent mainComponent;

    public GetDTO get() {
        return mainComponent.get();
    }

    public void set(Map<String, String> param) {
        SetParam setParam = new SetParam();
        setParam.setIndex(Integer.valueOf(param.get("index")));
        List<Integer> list = new ArrayList<>();
        param.remove("index");
        for (Map.Entry<String, String> entry : param.entrySet()) {
            list.add(Integer.valueOf(entry.getKey()));
        }
        setParam.setBiggerIndex(list);
        mainComponent.set(setParam);
    }

    public List<List<String>> download() {
        return mainComponent.download();
    }
}
