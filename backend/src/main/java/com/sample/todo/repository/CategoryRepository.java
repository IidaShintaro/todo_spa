package com.sample.todo.repository;

import com.sample.todo.entity.CategoryEntity;
import com.sample.todo.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final CategoryMapper mapper;

    // カテゴリーマスタ全件取得
    public List<CategoryEntity> categoryAll() { return this.mapper.categoryAll(); }
}
