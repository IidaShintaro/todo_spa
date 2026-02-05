package com.sample.todo.mapper;

import com.sample.todo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // ユーザー検索
    UserEntity findByUsername(String username);

}
