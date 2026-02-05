-- ステータスマスタ
INSERT INTO status_master (status_name) VALUES ('未実施');
INSERT INTO status_master (status_name) VALUES ('進行中');
INSERT INTO status_master (status_name) VALUES ('完了');
INSERT INTO status_master (status_name) VALUES ('保留');

-- カテゴリーマスタ
INSERT INTO category_master (category_name) VALUES ('仕事');
INSERT INTO category_master (category_name) VALUES ('勉強');
INSERT INTO category_master (category_name) VALUES ('趣味');
INSERT INTO category_master (category_name) VALUES ('家事');

-- タスク情報
INSERT INTO task_table (category_id, task_name, detail, status_id, deadline, user_id)
VALUES
(1, 'プロジェクトの企画書作成', '新しいプロジェクトの企画書を作成する。', 1, '2026-02-10', 1);
INSERT INTO task_table (category_id, task_name, detail, status_id, deadline, user_id)
VALUES
(2, 'Spring Bootの学習', 'アノテーションの意味を調べる。', 2, '2026-02-15', 1);
INSERT INTO task_table (category_id, task_name, detail, status_id, deadline, user_id)
VALUES
(3, '旅行先に行く', '次の休暇の旅行先を決める。', 3, '2026-01-20', 2);
INSERT INTO task_table (category_id, task_name, detail, status_id, deadline, user_id)
VALUES
(4, '買い物に行く', 'パンと牛乳を買う。', 4, '2026-01-25', 2);

-- ユーザー情報
INSERT INTO user_table (username, password) VALUES ('user1', '$2a$10$.gYYNt2vNgsM41MkvdrBrOhmC19qPAXK75ETCbYSqpPyD5PamlD.2'); -- password: password1
INSERT INTO user_table (username, password) VALUES ('user2', '$2a$10$pUbOqkOxkyRQ4D0FmAiNquO8nXfdgGgMYfrLcIPYCjWBvPFYuzP4S'); -- password: password2