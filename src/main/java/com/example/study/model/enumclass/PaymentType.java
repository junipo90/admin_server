package com.example.study.model.enumclass;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {

    CASH(0, "현금", "현금으로 계산"),
    CARD(1, "카드", "카드로 계산");

    private Integer id;
    private String title;
    private String description;

}
