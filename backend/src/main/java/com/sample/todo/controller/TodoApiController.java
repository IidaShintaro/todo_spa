package com.sample.todo.controller;

import com.sample.todo.controller.dto.TodoDetailResponse;
import com.sample.todo.controller.dto.TodoRequest;
import com.sample.todo.controller.dto.TodoResponse;
import com.sample.todo.entity.TaskEntity;
import com.sample.todo.entity.UserEntity;
import com.sample.todo.security.LoginUser;
import com.sample.todo.service.CategoryService;
import com.sample.todo.service.StatusService;
import com.sample.todo.service.TaskService;
import com.sample.todo.service.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoApiController {

    private final TaskService taskService;
    private final CategoryService categoryService;
    private final StatusService statusService;
    private  final UserDetailsServiceImpl userDetailsService;

    // タスク一覧画面表示処理API
    @GetMapping
    public List<TodoResponse> listTask(@AuthenticationPrincipal LoginUser loginUser){
        // ログイン中のユーザーID取得
        Long userId = loginUser.getUserId();

        return taskService.findAll(userId).stream()
                .map(TodoResponse::new)
                .toList();
    }

    // タスク検索処理API
    @GetMapping("/search")
    public List<TodoResponse> searchTask(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long statusId, @AuthenticationPrincipal LoginUser loginUser) {
        // ログイン中のユーザーID取得
        Long userId = loginUser.getUserId();

        return taskService.searchTask(categoryId, statusId, userId).stream()
                .map(TodoResponse::new)
                .toList();
    }

    // タスク詳細画面表示処理API
    @GetMapping("/detail/{id}")
    public ResponseEntity<TodoDetailResponse> detailTask(@PathVariable Long id) {
        TaskEntity task = taskService.findById(id);
        Map<Long, String> categoriesMap = categoryService.categoryMap();
        Map<Long, String> statusesMap = statusService.statusMap();

        TodoDetailResponse response = new TodoDetailResponse();
        response.setTodoResponse(new TodoResponse(task));
        response.setCategoryMap(categoriesMap);
        response.setStatusMap(statusesMap);

        return ResponseEntity.ok(response);
    }

    // タスク更新処理API
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TodoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "入力に誤りがあります。"));
        }
        try {
            taskService.updateTask(id, request);
            return ResponseEntity.ok(Map.of("message", "タスクが正常に更新されました."));
        } catch (Exception e) {
            log.error("処理中にエラーが発生しました：", e);
            return ResponseEntity.status(500).body(Map.of("errorMessage", "エラーメッセージ: " + e.getMessage()));
        }

    }

    // タスク削除処理API
    @PostMapping("/delete/{id}")
    public ResponseEntity <?> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok(Map.of("message", "タスクが正常に削除されました。"));
        } catch (Exception e) {
            log.error("処理中にエラーが発生しました：", e);
            return ResponseEntity.status(500).body(Map.of("errorMessage", "エラーメッセージ: " + e.getMessage()));
        }
    }

    // マスタデータ取得処理API
    @GetMapping("/masters")
    public ResponseEntity<?> getMasterData() {
        try {
            Map<Long, String> categoryMap = categoryService.categoryMap();
            Map<Long, String> statusMap = statusService.statusMap();

            return ResponseEntity.ok(Map.of(
                    "categoryMap", categoryMap,
                    "statusMap", statusMap
            ));
        } catch (Exception e) {
            log.error("処理中にエラーが発生しました：", e);
            return ResponseEntity.status(500).body(Map.of("errorMessage", "エラーメッセージ: " + e.getMessage()));
        }
    }

    // タスク新規作成処理API
    @PostMapping("/create")
    public ResponseEntity<?> createTask(@Valid @RequestBody TodoRequest request, BindingResult result, @AuthenticationPrincipal LoginUser loginUser) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(Map.of("errorMessage", "入力に誤りがあります。"));
        }

        try {
            // ログイン中のユーザー情報を取得してリクエストにセット
            request.setUserId(loginUser.getUserId());

            taskService.createTask(request);
            return ResponseEntity.ok(Map.of("message", "タスクが正常に作成されました。"));
        } catch (Exception e) {
            log.error("処理中にエラーが発生しました：", e);
            return ResponseEntity.status(500).body(Map.of("errorMessage", "エラーメッセージ: " + e.getMessage()));
        }
    }

}
