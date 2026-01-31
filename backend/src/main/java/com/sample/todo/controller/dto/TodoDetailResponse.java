package com.sample.todo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDetailResponse {
    private TodoResponse todoResponse;
    private Map<Long, String> categoryMap;
    private Map<Long, String> statusMap;
}
