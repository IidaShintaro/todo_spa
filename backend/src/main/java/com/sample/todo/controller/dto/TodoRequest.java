package com.sample.todo.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TodoRequest {

    // カテゴリーID
    @NotNull
    private Long categoryId;

    // タスク名
    @NotBlank
    private String task;

    // 詳細
    private String detail;

    // ステータスID
    @NotNull
    private Long statusId;

    // 期限日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
}
