package com.sample.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    // カテゴリーID
    private Long id;

    // カテゴリー名
    private String categoryName;
}
