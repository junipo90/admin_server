package com.example.study.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchParameter {
    private String account;
    private String email;
    private int page;

}
