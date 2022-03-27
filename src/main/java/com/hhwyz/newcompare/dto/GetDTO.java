package com.hhwyz.newcompare.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetDTO {
    private Integer index;
    private List<String> thisList;
}
