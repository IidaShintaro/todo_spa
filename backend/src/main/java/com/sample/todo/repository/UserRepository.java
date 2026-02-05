package com.sample.todo.repository;

import com.sample.todo.entity.UserEntity;
import com.sample.todo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper mapper;

    // ユーザー検索メソッド
    public UserEntity findByUsername(String username) { return this.mapper.findByUsername(username); }

}
