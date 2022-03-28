package com.hhwyz.newcompare.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author erniu.wzh
 * @date 2022/3/23 14:45
 */
@Data
public class TreeNode {
    private String middle;
    private List<String> listToOrder = new ArrayList<>();
    private List<String> leftList = new ArrayList<>();
    private List<String> rightList = new ArrayList<>();
    private TreeNode leftNode;
    private TreeNode rightNode;

    public static TreeNode generateOneNode(List<String> listToOrder) {
        if (listToOrder == null || listToOrder.isEmpty()) {
            return null;
        }
        TreeNode one = new TreeNode();
        Collections.shuffle(listToOrder);
        one.setMiddle(listToOrder.get(0));
        listToOrder.remove(0);
        one.setListToOrder(listToOrder);
        return one;
    }

    public TwoDTO getTwo() {
        if (!this.listToOrder.isEmpty()) {
            String item = this.listToOrder.get(0);
            TwoDTO twoDTO = new TwoDTO();
            twoDTO.setMiddle(middle);
            twoDTO.setItem(item);
            twoDTO.setTreeNode(this);
            return twoDTO;
        }
        if (leftNode != null) {
            TwoDTO result = leftNode.getTwo();
            if (result != null) {
                return result;
            }
        }
        if (rightNode != null) {
            return rightNode.getTwo();
        }
        return null;
    }

    public void set(String item, String leftOrRight) {
        this.listToOrder.remove(0);
        if ("left".equals(leftOrRight)) {
            this.leftList.add(item);
        } else if ("right".equals(leftOrRight)) {
            this.rightList.add(item);
        }
        if (this.listToOrder.isEmpty()) {
            this.leftNode = generateOneNode(leftList);
            this.rightNode = generateOneNode(rightList);
        }
    }

    public void show(List<String> staticList) {
        if (this.leftNode != null) {
            this.leftNode.show(staticList);
        }
        staticList.add(this.middle);
        if (this.rightNode != null) {
            this.rightNode.show(staticList);
        }
    }

    public TreeNode copy() {
        TreeNode leftNode = null;
        TreeNode rightNode = null;
        if (this.leftNode != null) {
            leftNode = this.leftNode.copy();
        }
        if (this.rightNode != null) {
            rightNode = this.rightNode.copy();
        }
        TreeNode node = new TreeNode();
        node.setMiddle(this.middle);
        node.setListToOrder(new ArrayList<>(this.listToOrder));
        node.setLeftList(new ArrayList<>(this.leftList));
        node.setRightList(new ArrayList<>(this.rightList));
        node.setLeftNode(leftNode);
        node.setRightNode(rightNode);
        return node;
    }

    public boolean worseRight() {
        if (this.listToOrder.isEmpty()) {
            if (this.leftNode == null && this.rightNode == null) {
                return true;
            }
            boolean b = true;
            boolean c = true;
            if (this.leftNode != null) {
                b = this.leftNode.worseRight();
            }
            if (this.rightNode != null) {
                c = this.rightNode.worseRight();
            }
            return b && c;
        } else {
            return this.leftList.isEmpty() && this.rightList.isEmpty();
        }
    }

    public int worseTimes() {
        if (this.leftNode == null && this.rightNode == null) {
            return this.listToOrder.size() * (this.listToOrder.size() + 1) / 2;
        }
        int b = 0;
        int c = 0;
        if (this.leftNode != null) {
            b = this.leftNode.worseTimes();
        }
        if (this.rightNode != null) {
            c = this.rightNode.worseTimes();
        }
        return b + c;
    }
}
