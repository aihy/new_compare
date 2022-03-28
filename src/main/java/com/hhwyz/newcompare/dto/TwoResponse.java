package com.hhwyz.newcompare.dto;

import lombok.Data;

/**
 * @author erniu.wzh
 * @date 2022/3/23 18:12
 */
@Data
public class TwoResponse {
    private String middle;
    private String item;
    private String ticket;

    public TwoResponse(String middle, String item, String ticket) {
        this.middle = middle;
        this.item = item;
        this.ticket = ticket;
    }
}
