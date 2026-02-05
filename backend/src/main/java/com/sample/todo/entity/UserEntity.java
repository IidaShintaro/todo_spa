package com.sample.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    // ユーザーID
    private Long id;

    // ユーザー名
    private String username;

    // パスワード
    private String password;
}
