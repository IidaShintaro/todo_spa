import { BrowserRouter, Routes, Route } from "react-router-dom";
import TaskList from "./TaskList";
import TaskDetail from "./TaskDetail";
import TaskCreate from "./TaskCreate";
import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* 一覧画面 */}
        <Route path="/" element={<TaskList />} />
        {/* 詳細画面 */}
        <Route path="/detail/:id" element={<TaskDetail />} />
        {/* 登録画面 */}
        <Route path="/create" element={<TaskCreate />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
