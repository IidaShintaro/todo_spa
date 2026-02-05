package com.sample.todo.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

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
    private LocalDate deadline;

    // 作成日時
    private LocalDateTime createdAt;

    // 更新日時
    private LocalDateTime updatedAt;

    // 削除フラグ
    private  Boolean deleteFlag;

    // ユーザーID
    private Long userId;

}
