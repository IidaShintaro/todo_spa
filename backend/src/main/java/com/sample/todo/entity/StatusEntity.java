package com.sample.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusEntity {

    // ステータスID
    private Long id;

    // ステータス名
    private String statusName;
}
