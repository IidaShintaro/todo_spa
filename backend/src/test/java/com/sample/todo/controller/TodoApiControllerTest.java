package com.sample.todo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import com.sample.todo.annotation.CsvDatabaseSetup;
import com.sample.todo.annotation.SpringBootTestBaseForJUnit5;
import com.sample.todo.controller.dto.TodoRequest;
import com.sample.todo.controller.dto.TodoResponse;
import com.sample.todo.dataset.CsvDbUnitExtension;
import com.sample.todo.repository.CategoryRepository;
import com.sample.todo.repository.StatusRepository;
import com.sample.todo.repository.TaskRepository;
import com.sample.todo.service.CategoryService;
import com.sample.todo.service.StatusService;
import com.sample.todo.service.TaskService;

/** ToDoアプリコントローラテスト. */
@SpringBootTestBaseForJUnit5
@ExtendWith(CsvDbUnitExtension.class)
@DisplayName("ToDoアプリコントローラテスト.")
public class TodoApiControllerTest {

  /**
   * ToDoアプリコントローラ.
   */
  @Autowired
  private TodoApiController target;

  @Mock
  @Spy
  /** タスクサービス. */
  @MockitoSpyBean
  private TaskService taskService;

  /**
   * カテゴリサービス.
   */
  @MockitoSpyBean
  private CategoryService categoryService;

  /**
   * ステータスサービス.
   */
  @MockitoSpyBean
  private StatusService statusService;

  /**
   * タスクリポジトリ.
   */
  @MockitoSpyBean
  private TaskRepository taskRepository;

  /**
   * カテゴリリポジトリ.
   */
  @MockitoSpyBean
  private CategoryRepository categoryRepository;

  /**
   * ステータスリポジトリ.
   */
  @MockitoSpyBean
  private StatusRepository statusRepository;

  /**
   * タスク一覧画面表示処理APIテスト.
   */
  @Nested
  @DisplayName("タスク一覧画面表示処理APIテスト")
  public class ListTaskTest {

    /**
     * <pre>
     *     メソッド名：
     *     テスト内容：~~~する
     *     テスト条件：
     *     テスト結果：
     *     　　　　- ●●が返却される
     * </pre>
     */
    @ParameterizedTest
    @MethodSource("selectParam")
    @CsvDatabaseSetup("dbunit/init/todo/test_01_ok")
    @DisplayName("~~~する")
    public void test_01_ok(String a, int b) {

      // モック設定
      Mockito.when(taskService.findAll()).thenReturn(new ArrayList<>());
      Mockito.doReturn(new ArrayList<>(), Arrays.asList("", "")).when(taskService).findAll();
      Mockito.doThrow(RuntimeException.class).when(taskService).findAll();
      Mockito.doReturn(new TodoResponse()).when(taskService).findById(Mockito.any());
      Mockito.doReturn(new TodoResponse()).when(taskService).findById(1L);
      Mockito.doReturn(null).when(taskService).findById(2L);

      // 期待結果
      var tr1 = createFindAllTodoResponse(1L, "", "");
      var tr2 = createFindAllTodoResponse(2L, "", "");
      var tr3 = createFindAllTodoResponse(3L, "", "");
      List<TodoResponse> expected = List.of(tr1, tr2, tr3);

      // テスト実施
      List<TodoResponse> actual = TodoApiControllerTest.this.target.listTask();

      // 結果確認
      Assertions.assertThat(actual).isEqualTo(expected);
      Mockito.verify(taskRepository, Mockito.times(b)).findAll();
      TodoRequest todoRequest = new TodoRequest();
      todoRequest.setTask("");
      Mockito.verify(taskRepository, Mockito.times(1)).updateTask(1L, todoRequest);
      Mockito.verify(taskRepository, Mockito.times(1)).updateTask(Mockito.any(), Mockito.any());

    }

    /**
     * <pre>
     *     メソッド名：
     *     テスト内容：
     *     テスト条件：
     *     テスト結果：
     *     　　　　- ●●が返却される
     * </pre>
     */
    @Test
    @CsvDatabaseSetup("")
    @DisplayName("")
    public void test_02_ok() {

      // 期待結果

      // テスト実施

      // 結果確認

    }

    private TodoResponse createFindAllTodoResponse(long a, String b, String c) {
      return TodoResponse.builder().id(a).category(b).status(c).deadline(LocalDate.now())
          .detail("詳細").build();
    }

    static Stream<Arguments> selectParam() {
      return Stream.of(Arguments.of("", 1), Arguments.of("", 2), Arguments.of("", 3));
    }
  }

  /**
   * ●●テスト.
   */
  @Nested
  @DisplayName("")
  public class XXTest {
    /**
     * <pre>
     *     メソッド名：
     *     テスト内容：
     *     テスト条件：
     *     テスト結果：
     *     　　　　- ●●が返却される
     * </pre>
     */
    @Test
    @CsvDatabaseSetup("")
    @DisplayName("")
    public void test_xx_ok() {

      // リクエスト作成

      // 期待結果

      // テスト実施

      // 結果確認

    }
  }
}
