package com.sample.todo.repository;

import com.sample.todo.controller.dto.TodoRequest;
import com.sample.todo.entity.TaskEntity;
import com.sample.todo.entity.UserEntity;
import com.sample.todo.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final TaskMapper mapper;

    // 全件取得メソッド
    public List<TaskEntity> findAll(Long userId) {
        return this.mapper.findAll(userId);
    }

    // タスク検索メソッド
    public List<TaskEntity> searchTask(Long categoryId, Long statusId, Long userId) { return this.mapper.searchTask(categoryId, statusId, userId); }

    // 1件取得メソッド
    public TaskEntity findById(Long id) {
        return this.mapper.findById(id);
    }

    // 更新メソッド
    @Validated
    public int updateTask(Long id, TodoRequest todoRequest) { return this.mapper.updateTask(id, todoRequest); }

    // 論理削除メソッド
    @Validated
    public int deleteTask(TaskEntity task) { return this.mapper.deleteTask(task); }

    // 新規作成メソッド
    @Validated
    public int createTask(TodoRequest todoRequest) {return this.mapper.createTask(todoRequest); }
}
