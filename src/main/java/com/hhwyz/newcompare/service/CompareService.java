package com.hhwyz.newcompare.service;

import com.hhwyz.newcompare.dto.TreeNode;
import com.hhwyz.newcompare.dto.TwoDTO;
import com.hhwyz.newcompare.dto.TwoResponse;
import com.hhwyz.newcompare.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author erniu.wzh
 * @date 2022/3/23 14:03
 */
@Service
public class CompareService {
    static TreeNode root;
    static List<String> resultList;
    static Map<String, TreeNode> ticketMap;
    static List<TreeNode> undoList;
    static List<String> undoTicket;
    static Integer times;
    static Integer worstTimes;
    static List<List<String>> lilyList;
    static List<String> resultLily;
    @Resource
    private MainService mainService;

    public void init(List<String> list) {
        root = TreeNode.generateOneNode(list);
        resultList = new ArrayList<>();
        ticketMap = new HashMap<>();
        undoTicket = new ArrayList<>();
        undoList = new ArrayList<>();
        times = 0;
        worstTimes = root.worseTimes();
    }

    public TwoResponse getTwo() throws NoSuchAlgorithmException {
        if (root == null) {
            lilyList = mainService.download();
            init(lilyList.get(lilyList.size() - 1));
            lilyList.remove(lilyList.size() - 1);
        }
        TwoDTO result;
        while (true) {
            result = root.getTwo();
            if (result == null) {
                if (resultLily == null) {
                    resultLily = new ArrayList<>();
                }
                List<String> r = getResult();
                resultLily.addAll(r);
                if (lilyList.isEmpty()) {
                    return null;
                }
                init(lilyList.get(lilyList.size() - 1));
                lilyList.remove(lilyList.size() - 1);
            } else {
                break;
            }
        }
        String ticket = Md5Util.md5ToBase64(String.format("%s_%s", result.getMiddle(), result.getItem()).getBytes(StandardCharsets.UTF_8));
        if (!ticketMap.containsKey(ticket)) {
            ticketMap.put(ticket, result.getTreeNode());
            undoTicket.add(ticket);
        }
        return new TwoResponse(result.getMiddle(), result.getItem(), ticket);
    }

    public String getTimes() {
        if (root.worseRight()) {
            worstTimes = root.worseTimes();
        }
        return String.format("次数=%d 最坏剩余次数=%d", times, worstTimes);
    }

    public void set(String item, String leftOrRight, String ticket) {
        if (!ticketMap.containsKey(ticket)) {
            return;
        }
        times += 1;
        undoList.add(root.copy());
        if (undoList.size() > 10) {
            undoList.remove(0);
        }
        ticketMap.get(ticket).set(item, leftOrRight);
        ticketMap.remove(ticket);
        worstTimes -= 1;
    }

    public void undo() {
        times -= 1;
        root = undoList.get(undoList.size() - 1);
        undoList.remove(undoList.size() - 1);
        ticketMap.remove(undoTicket.get(undoTicket.size() - 1));
        worstTimes += 1;
    }

    public boolean canUndo() {
        return !undoList.isEmpty() && times > 0;
    }

    public List<String> getResult() {
        resultList.clear();
        root.show(resultList);
        return resultList;
    }

    public List<String> download() {
        return resultLily;
    }
}
