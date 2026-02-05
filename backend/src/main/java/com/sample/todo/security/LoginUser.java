package com.sample.todo.security;

import com.sample.todo.entity.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

    private final Long userId;

    public LoginUser(UserEntity userEntity) {

        super(userEntity.getUsername(), userEntity.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.userId = userEntity.getId();
    }
    public Long getUserId() {
        return userId;
    }
}
