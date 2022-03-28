package com.hhwyz.newcompare.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hhwyz.newcompare.dto.TwoResponse;
import com.hhwyz.newcompare.service.CompareService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author erniu.wzh
 * @date 2022/3/28 17:14
 */
@RestController
@RequestMapping("/compare")
public class CompareController {
    @Resource
    private CompareService compareService;

    @GetMapping("/get")
    public JSONObject getTwo() throws NoSuchAlgorithmException {
        TwoResponse twoResponse = compareService.getTwo();
        boolean canUndo = compareService.canUndo();
        String timesString = compareService.getTimes();
        JSONObject result = new JSONObject();
        result.put("twoResponse", twoResponse);
        result.put("canUndo", canUndo);
        result.put("timesString", timesString);
        return result;
    }

    @GetMapping("/set")
    public void set(String item, String leftOrRight, String ticket) {
        compareService.set(item, leftOrRight, ticket);
    }

    @GetMapping("/show")
    public List<String> show() {
        return compareService.download();
    }

    @GetMapping("/undo")
    public void undo() {
        compareService.undo();
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> lilyResult = compareService.download();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("lily.json", "UTF-8"));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
            bufferedWriter.write(JSON.toJSONString(lilyResult));
        }
    }
}
