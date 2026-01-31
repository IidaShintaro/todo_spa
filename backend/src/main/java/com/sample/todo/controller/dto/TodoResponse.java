package com.sample.todo.controller.dto;

import com.sample.todo.entity.TaskEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {

    // タスクID
    private Long id;

    // カテゴリーID
    private Long categoryId;

    // カテゴリー名
    private String category;

    // タスク名
    private String task;

    // 詳細
    private String detail;

    // ステータスID
    private Long statusId;

    // ステータス名
    private String status;

    // 期限日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    public TodoResponse(TaskEntity entity) {
        this.id = entity.getId();
        this.categoryId = entity.getCategoryId();
        this.category = entity.getCategory();
        this.task = entity.getTask();
        this.detail = entity.getDetail();
        this.statusId = entity.getStatusId();
        this.status = entity.getStatus();
        this.deadline = entity.getDeadline();
    }


}
