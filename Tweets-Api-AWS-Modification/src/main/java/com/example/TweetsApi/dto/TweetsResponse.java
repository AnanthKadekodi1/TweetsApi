package com.example.TweetsApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetsResponse {
    private Long id;
    private String content;
    private String userName;
}