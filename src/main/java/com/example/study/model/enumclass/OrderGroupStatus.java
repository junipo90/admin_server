package com.example.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupStatus {
    ORDERING(0, "등록", "주문 등록 상태"),
    COMPLETE(1, "완료", "주문 완료 상태"),
    CONFIRM(2, "확인", "주문 확인 상태");

    private Integer id;
    private String title;
    private String description;
}
