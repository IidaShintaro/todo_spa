import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

interface TodoResponse {
  id: number;
  categoryId: number;
  category: string;
  task: string;
  detail: string;
  statusId: number;
  status: string;
  deadline: string;
}

function TaskDetail() {
  const { id } = useParams(); // URLパラメータからIDを取得
  const [todo, setTodo] = useState<TodoResponse | null>(null);
  const [categoryMap, setCategoryMap] = useState<{ [key: string]: string }>({});
  const [statusMap, setStatusMap] = useState<{ [key: string]: string }>({});
  const [successMessage, setSuccessMessage] = useState<string | null>(null);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  // タスク詳細取得関数
  useEffect(() => {
    const fetchTodo = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/todos/detail/${id}`,
        );
        const data = await response.json();
        setTodo(data.todoResponse);
        setCategoryMap(data.categoryMap);
        setStatusMap(data.statusMap);
      } catch (error) {
        console.error("エラーが発生しました:", error);
        setErrorMessage("タスクの取得に失敗しました。");
      }
    };
    fetchTodo();
  }, [id]);

  // タスク編集入力変更ハンドラ
  const handleChange = (
    e: React.ChangeEvent<
      HTMLSelectElement | HTMLInputElement | HTMLTextAreaElement
    >,
  ) => {
    if (!todo) return;
    // 現在のタスクの状態をコピーしつつ、書き換わった場所だけ更新する
    setTodo({ ...todo, [e.target.name]: e.target.value });
  };

  // タスク更新関数
  const fetchUpdate = async () => {
    if (!todo) return;

    try {
      const response = await fetch(
        `http://localhost:8080/api/todos/update/${id}`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(todo),
        },
      );

      if (response.ok) {
        setSuccessMessage("タスクの更新に成功しました。");
      } else {
        setErrorMessage("タスクの更新に失敗しました。");
      }
    } catch (error) {
      console.error("エラーが発生しました:", error);
      setErrorMessage("通信エラーが発生しました。");
    }
  };

  return (
    <div className="container-fluid mt-4">
      <div className="row">
        <div className="col-12">
          <h2>タスク詳細</h2>
          {successMessage && (
            <div className="alert alert-success mx-3">{successMessage}</div>
          )}
          {errorMessage && (
            <div className="alert alert-danger mx-3">{errorMessage}</div>
          )}

          <div className="card">
            <div className="card-header">
              <h5 className="card-title">
                {todo ? <strong>{todo.task}</strong> : "読み込み中..."}
              </h5>
            </div>
            <div className="card-body">
              {todo ? (
                <div className="mb-3">
                  <table className="table table-bordered">
                    <thead></thead>
                    <tbody>
                      <tr>
                        <th>カテゴリー</th>
                        <td>
                          <select
                            className="form-select"
                            name="categoryId"
                            value={todo.categoryId}
                            onChange={handleChange}
                          >
                            {Object.entries(categoryMap).map(([id, name]) => (
                              <option key={id} value={id}>
                                {name}
                              </option>
                            ))}
                          </select>
                        </td>
                      </tr>
                      <tr>
                        <th>詳細</th>
                        <td>
                          <textarea
                            className="form-control"
                            name="detail"
                            value={todo.detail}
                            onChange={handleChange}
                            rows={5}
                          />
                        </td>
                      </tr>
                      <tr>
                        <th>期限</th>
                        <td>
                          <input
                            type="date"
                            className="form-control"
                            name="deadline"
                            value={todo.deadline}
                            onChange={handleChange}
                          />
                        </td>
                      </tr>
                      <tr>
                        <th>ステータス</th>
                        <td>
                          <select
                            className="form-select"
                            name="statusId"
                            value={todo.statusId}
                            onChange={handleChange}
                          >
                            {Object.entries(statusMap).map(([id, name]) => (
                              <option key={id} value={id}>
                                {name}
                              </option>
                            ))}
                          </select>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              ) : (
                <p>読み込み中...</p>
              )}
            </div>
          </div>
          <div className="card-footer">
            <button className="btn btn-primary mt-3 mr-3" onClick={fetchUpdate}>
              更新
            </button>
            <Link to="/" className="btn btn-secondary mt-3">
              戻る
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TaskDetail;
