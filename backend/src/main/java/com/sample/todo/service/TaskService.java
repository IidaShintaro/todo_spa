package com.sample.todo.service;

import com.sample.todo.controller.dto.TodoRequest;
import com.sample.todo.entity.TaskEntity;
import com.sample.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    // 全件取得メソッド
    public List<TaskEntity> findAll(){
        return repository.findAll();
    }

    // タスク検索メソッド
    public List<TaskEntity> searchTask(Long categoryId, Long statusId) {
        return repository.searchTask(categoryId, statusId);
    }

    // 1件取得メソッド
    public TaskEntity findById(Long id) {
        return repository.findById(id);
    }

    // タスク更新メソッド
    public int updateTask(Long id, TodoRequest todoRequest) {
        return repository.updateTask(id, todoRequest);
    }

    // タスク削除メソッド
    public int deleteTask(Long id) {
        TaskEntity task = new TaskEntity();
        task.setId(id);
        task.setDeleteFlag(true);
        return repository.deleteTask(task);
    }

    // タスク新規作成メソッド
    public int createTask(TodoRequest todoRequest) {
        return repository.createTask(todoRequest);
    }

}
