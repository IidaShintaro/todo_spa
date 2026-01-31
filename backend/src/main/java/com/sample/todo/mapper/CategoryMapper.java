package com.sample.todo.mapper;

import com.sample.todo.entity.CategoryEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {

    // カテゴリーマスタ全件取得
    List<CategoryEntity> categoryAll();
}
