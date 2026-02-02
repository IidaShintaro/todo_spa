import { useEffect, useState } from "react";
import "./App.css";
import { Link } from "react-router-dom";

interface TodoResponse {
  id: number;
  category: string;
  task: string;
  status: string;
  deadline: string;
}

function TaskList() {
  const [todos, setTodos] = useState<TodoResponse[]>([]);
  const [categoryMap, setCategoryMap] = useState<{ [key: string]: string }>({});
  const [statusMap, setStatusMap] = useState<{ [key: string]: string }>({});
  const [successMessage, setSuccessMessage] = useState<string | null>(null);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [searchConditions, setSearchConditions] = useState({
    categoryId: "",
    statusId: "",
  });

  // 削除対象のIDを保持するState
  const [deleteTargetId, setDeleteTargetId] = useState<number | null>(null);

  // タスク一覧取得関数
  const fetchTodos = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/todos");
      const data = await response.json();
      setTodos(data);
    } catch (error) {
      setErrorMessage("データ取得に失敗しました");
      console.error("Error fetching todos:", error);
    }
  };

  // マスタデータ取得関数
  const fetchMaster = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/todos/masters`);
      const data = await response.json();
      setCategoryMap(data.categoryMap);
      setStatusMap(data.statusMap);
    } catch (error) {
      console.error("エラーが発生しました:", error);
      setErrorMessage("マスタデータの取得に失敗しました。");
    }
  };

  // 検索実行関数
  const feachSearch = async () => {
    try {
      const params = new URLSearchParams();
      if (searchConditions.categoryId)
        params.append("categoryId", searchConditions.categoryId);
      if (searchConditions.statusId)
        params.append("statusId", searchConditions.statusId);

      const response = await fetch(
        `http://localhost:8080/api/todos/search?${params.toString()}`,
      );
      const data = await response.json();
      setTodos(data);
    } catch (error) {
      setErrorMessage("検索に失敗しました");
      console.error("Error searching todos:", error);
    }
  };

  // 削除実行関数
  const fetchDelete = async () => {
    if (deleteTargetId === null) return;

    try {
      const response = await fetch(
        `http://localhost:8080/api/todos/delete/${deleteTargetId}`,
        {
          method: "POST",
        },
      );

      if (response.ok) {
        setSuccessMessage("削除に成功しました");
        fetchTodos(); // リストを再取得
      } else {
        setErrorMessage("削除に失敗しました");
      }
    } catch (error) {
      setErrorMessage("通信エラーが発生しました");
      console.error("Error deleting todo:", error);
    } finally {
      setDeleteTargetId(null); // IDをクリア
    }
  };

  useEffect(() => {
    fetchTodos();
    fetchMaster();
  }, []);

  return (
    <div className="mt-4">
      <h1 className="my-3 ml-3">タスク一覧</h1>

      <div className="mb-3 ml-3">
        <Link to="/create">
          <button className="btn btn-primary">新規作成</button>
        </Link>
      </div>

      <label className="mx-3">カテゴリー</label>
      <select
        className="mx-3"
        value={searchConditions.categoryId}
        onChange={(e) =>
          setSearchConditions({
            ...searchConditions,
            categoryId: e.target.value,
          })
        }
      >
        <option value="">全て表示</option>
        {Object.entries(categoryMap).map(([id, name]) => (
          <option key={id} value={id}>
            {name}
          </option>
        ))}
      </select>

      <label className="mx-3">ステータス</label>
      <select
        className="mx-3"
        value={searchConditions.statusId}
        onChange={(e) =>
          setSearchConditions({ ...searchConditions, statusId: e.target.value })
        }
      >
        <option value="">全て表示</option>
        {Object.entries(statusMap).map(([id, name]) => (
          <option key={id} value={id}>
            {name}
          </option>
        ))}
      </select>

      <button className="btn btn-secondary mx-3" onClick={feachSearch}>
        検索
      </button>

      {successMessage && (
        <div className="alert alert-success mx-3">{successMessage}</div>
      )}
      {errorMessage && (
        <div className="alert alert-danger mx-3">{errorMessage}</div>
      )}

      <div className="col-12 ml-3">
        <table className="table table-hover">
          <thead className="table-primary">
            <tr>
              <th>No.</th>
              <th>カテゴリー</th>
              <th>タスク名</th>
              <th>期限</th>
              <th>ステータス</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {todos.map((todo) => (
              <tr key={todo.id}>
                <td>{todo.id}</td>
                <td>{todo.category}</td>
                <td>{todo.task}</td>
                <td>{todo.deadline}</td>
                <td>{todo.status}</td>
                <td>
                  <Link
                    to={`/detail/${todo.id}`}
                    className="btn btn-success btn-sm text-light"
                  >
                    詳細
                  </Link>
                </td>
                <td>
                  <button
                    type="button"
                    className="btn btn-danger btn-sm"
                    data-bs-toggle="modal"
                    data-bs-target="#deleteModal"
                    onClick={() => setDeleteTargetId(todo.id)} // 削除対象をセット
                  >
                    削除
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div
        className="modal fade"
        id="deleteModal"
        tabIndex={-1}
        aria-labelledby="deleteModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="deleteModalLabel">
                削除確認
              </h1>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              本当に削除してもよろしいですか？ (ID: {deleteTargetId})
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                戻る
              </button>
              <button
                type="button"
                className="btn btn-danger"
                onClick={fetchDelete}
                data-bs-dismiss="modal"
              >
                削除する
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TaskList;
