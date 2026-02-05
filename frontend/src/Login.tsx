import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const fetchLogin = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });
      if (response.ok) {
        // ログイン成功時の処理
        console.log("ログイン成功");
        const data = await response.json();
        localStorage.setItem("token", data.token);
        localStorage.setItem("username", username);
        navigate("/list"); // 一覧画面へ遷移
      } else {
        setErrorMessage(
          "ログインに失敗しました。ユーザー名またはパスワードを確認してください。",
        );
      }
    } catch (error) {
      setErrorMessage("ログイン中にエラーが発生しました。");
      console.error("Error during login:", error);
    }
  };

  return (
    <div className="container mt-5">
      <div className="card">
        <div className="card-body">
          <h3 className="card-title text-center">ログイン</h3>
          {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>}

          <div>
            <label>ユーザー名</label>
            <input
              className="form-control"
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>
          <div>
            <label>パスワード</label>
            <input
              className="form-control"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button className="btn btn-primary mt-3 w-100" onClick={fetchLogin}>
            ログイン
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;
