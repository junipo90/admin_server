package com.example.study.model.enumclass;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {

    BANK_TRANSFER(0, "계좌 이체", "계좌 이체 계산"),
    CARD(1, "카드", "카드 계산"),
    CHECK_CARD(2, "체크 카드", "체크 카드 계산");

    private Integer id;
    private String title;
    private String description;

}
