package com.sample.todo.repository;

import com.sample.todo.controller.dto.TodoRequest;
import com.sample.todo.entity.TaskEntity;
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
    public List<TaskEntity> findAll() {
        return this.mapper.findAll();
    }

    // タスク検索メソッド
    public List<TaskEntity> searchTask(Long categoryId, Long statusId) { return this.mapper.searchTask(categoryId, statusId); }

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
