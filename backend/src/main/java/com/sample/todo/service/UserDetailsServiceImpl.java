package com.sample.todo.service;

import com.sample.todo.entity.UserEntity;
import com.sample.todo.repository.UserRepository;
import com.sample.todo.security.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    // ユーザー認証メソッド
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
        }

        return new LoginUser(userEntity);

    }

    // ユーザー検索メソッド
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
