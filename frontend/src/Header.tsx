import { Outlet } from "react-router-dom";

const Header = () => {
  const username = localStorage.getItem("username");

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    window.location.href = "/"; // ログイン画面へリダイレクト
  };

  return (
    <div>
      <nav
        style={{
          display: "flex",
          justifyContent: "space-between",
          padding: "10px",
          background: "#eee",
        }}
      >
        <span>
          <h4 className="my-2">Todoアプリ</h4>
        </span>
        <div className="d-flex align-items-center">
          {username && <span className="mx-3">ようこそ、{username}さん</span>}
          <button className="btn btn-danger" onClick={logout}>
            ログアウト
          </button>
        </div>
      </nav>

      <main>
        <Outlet />
      </main>
    </div>
  );
};
export default Header;
