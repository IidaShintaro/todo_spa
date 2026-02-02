package com.sample.todo.mapper;

import com.sample.todo.controller.dto.TodoRequest;
import com.sample.todo.controller.dto.TodoResponse;
import com.sample.todo.entity.TaskEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {

    // 全件取得
    List<TaskEntity> findAll();

    // タスク検索
    List<TaskEntity> searchTask(Long categoryId, Long statusId);

    // 1件取得
    TaskEntity findById(Long id);

    // 更新
    int updateTask(@Param("id")Long id, @Param("todo")TodoRequest todoRequest);

    // 論理削除
    int deleteTask(TaskEntity task);

    // 新規作成
    int createTask(TodoRequest todoRequest);

}
