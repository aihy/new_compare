package com.hhwyz.newcompare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    @Resource
    private MainService mainService;

    @GetMapping("/show")
    public List<String> show() throws IOException {
        return mainService.show();
    }

    @PostMapping("/submit")
    public String submit(@RequestParam Map<Integer, String> in) {
        System.out.println(in);
        return "OK";
    }
}
