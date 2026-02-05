import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./Login";
import TaskList from "./TaskList";
import TaskDetail from "./TaskDetail";
import TaskCreate from "./TaskCreate";
import Header from "./Header";
import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* ログイン画面 */}
        <Route path="/" element={<Login />} />

        <Route element={<Header />}>
          {/* 一覧画面 */}
          <Route path="/list" element={<TaskList />} />
          {/* 詳細画面 */}
          <Route path="/detail/:id" element={<TaskDetail />} />
          {/* 登録画面 */}
          <Route path="/create" element={<TaskCreate />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
