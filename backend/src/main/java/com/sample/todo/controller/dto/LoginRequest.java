package com.sample.todo.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {

    // ユーザー名
    @NotEmpty
    private String username;

    // パスワード
    @NotEmpty
    private String password;
}
