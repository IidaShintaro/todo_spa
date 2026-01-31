package com.sample.todo.service;

import com.sample.todo.entity.CategoryEntity;
import com.sample.todo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    // カテゴリーMap作成メソッド
    public Map<Long, String> categoryMap() {

        // カテゴリーマスタ全件取得
        List<CategoryEntity> categoryList = repository.categoryAll();

        // カテゴリーMap作成
        Map<Long, String> categoryMap = new HashMap();
        for (CategoryEntity category : categoryList) {
            categoryMap.put(category.getId(), category.getCategoryName());
        }

        return categoryMap;
    }
}
