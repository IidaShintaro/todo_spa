import { useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";

function TaskCreate() {
  const navigate = useNavigate();

  // フォーム初期値
  const [todo, setTodo] = useState({
    task: "",
    detail: "",
    deadline: "",
    statusId: 1,
    categoryId: 1,
  });

  const [categoryMap, setCategoryMap] = useState<{ [key: string]: string }>({});
  const [statusMap, setStatusMap] = useState<{ [key: string]: string }>({});
  const [successMessage, setSuccessMessage] = useState<string | null>(null);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  // マスタデータ取得関数
  useEffect(() => {
    const fetchMaster = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/todos/masters`,
          {
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${localStorage.getItem("token")}`, // トークンの添付
            },
          },
        );
        const data = await response.json();
        setCategoryMap(data.categoryMap);
        setStatusMap(data.statusMap);
      } catch (error) {
        console.error("エラーが発生しました:", error);
        setErrorMessage("マスタデータの取得に失敗しました。");
      }
    };
    fetchMaster();
  }, []);

  // タスク編集入力変更ハンドラ
  const handleChange = (
    e: React.ChangeEvent<
      HTMLSelectElement | HTMLInputElement | HTMLTextAreaElement
    >,
  ) => {
    // 現在のタスクの状態をコピーしつつ、書き換わった場所だけ更新する
    setTodo({ ...todo, [e.target.name]: e.target.value });
  };

  // タスク登録関数
  const fetchCreate = async () => {
    if (!todo) return;

    try {
      const response = await fetch(`http://localhost:8080/api/todos/create`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("token")}`, // トークンの添付
        },
        body: JSON.stringify(todo),
      });

      if (response.ok) {
        setSuccessMessage("タスクの登録に成功しました。");
        navigate("/list"); // 一覧画面に遷移
      } else {
        setErrorMessage("タスクの登録に失敗しました。");
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
          <h2>タスク登録</h2>

          {/* メッセージ表示 */}
          {successMessage && (
            <div className="alert alert-success mx-3">{successMessage}</div>
          )}
          {errorMessage && (
            <div className="alert alert-danger mx-3">{errorMessage}</div>
          )}

          {/* タスク登録フォーム */}
          <div className="card">
            <div className="card-header">
              <h5 className="card-title">新規タスク登録フォーム</h5>
            </div>
            <div className="card-body">
              {todo ? (
                <div className="mb-3">
                  <table className="table table-bordered">
                    <thead></thead>
                    <tbody>
                      <tr>
                        <th>タスク名</th>
                        <td>
                          <input
                            type="text"
                            className="form-control"
                            name="task"
                            value={todo.task}
                            onChange={handleChange}
                          />
                        </td>
                      </tr>
                      <tr>
                        <th>カテゴリー</th>
                        <td>
                          <select
                            className="form-select w-auto"
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
                            className="form-control w-auto"
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
                            className="form-select w-auto"
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
            <button className="btn btn-success mt-3 mx-3" onClick={fetchCreate}>
              登録
            </button>
            <Link to="/list" className="btn btn-secondary mt-3">
              戻る
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TaskCreate;
