package com.sample.todo.repository;

import com.sample.todo.entity.StatusEntity;
import com.sample.todo.mapper.StatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatusRepository {

    private final StatusMapper mapper;

    // カテゴリーマスタ全件取得
    public List<StatusEntity> statusAll() { return this.mapper.statusAll(); }
}
