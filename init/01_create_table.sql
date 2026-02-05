-- DROP
DROP TABLE IF EXISTS task_table;
DROP TABLE IF EXISTS status_master;
DROP TABLE IF EXISTS category_master;
DROP TABLE IF EXISTS user_table;

-- CREATE
-- タスク情報
CREATE TABLE task_table (
    id SERIAL PRIMARY KEY,
    category_id INTEGER DEFAULT 1 NOT NULL,
    task_name VARCHAR(100) NOT NULL,
    detail TEXT,
    status_id INTEGER DEFAULT 1 NOT NULL,
    deadline DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delete_flag BOOLEAN DEFAULT FALSE,
    user_id INTEGER NOT NULL
);

-- ステータスマスタ
CREATE TABLE status_master (
    id SERIAL PRIMARY KEY,
    status_name VARCHAR(50) NOT NULL
);

-- カテゴリーマスタ
CREATE TABLE category_master (
    id SERIAL PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

-- ユーザーテーブル
CREATE TABLE user_table (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);