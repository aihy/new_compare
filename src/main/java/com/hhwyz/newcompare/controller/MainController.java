package com.hhwyz.newcompare.controller;

import com.alibaba.fastjson.JSON;
import com.hhwyz.newcompare.dto.GetDTO;
import com.hhwyz.newcompare.service.MainService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
public class MainController {
    @Resource
    private MainService mainService;

    @GetMapping("/get")
    public GetDTO get() {
        return mainService.get();
    }

    @PostMapping("/submit")
    public void submit(@RequestParam Map<String, String> param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        mainService.set(param);
        response.sendRedirect("/index.html");
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<List<String>> lilyList = mainService.download();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("lily.json", "UTF-8"));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
            bufferedWriter.write(JSON.toJSONString(lilyList));
        }
    }
}
