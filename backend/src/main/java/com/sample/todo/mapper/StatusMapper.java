package com.sample.todo.mapper;

import com.sample.todo.entity.StatusEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatusMapper {

    // ステータスマスタ全件取得
    List<StatusEntity> statusAll();
}
