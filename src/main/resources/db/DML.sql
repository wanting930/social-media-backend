USE esun;

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE comments;
TRUNCATE TABLE posts;
TRUNCATE TABLE users;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (
    user_name,
    email,
    phone,
    password,
    biography
) VALUES (
    'Wendy',
    'wendy12345@example.com',
    '0912333444',
    '$2a$10$dW0P0MsgkE3.8QslJxjVOemHhkKfBspTEqlHfG4YoTHm.jGUSxzOm',
    '喜歡旅遊與寫程式的後端工程師'
);

INSERT INTO posts (
    user_id,
    content
) VALUES (
    1,
    '今天第一次完成 Vue + Spring Boot 的登入功能，好有成就感！'
);

INSERT INTO comments (
    user_id,
    post_id,
    content
) VALUES
(
    1,
    1,
    '一天一蘋果'
),
(
    1,
    1,
    '醫生遠離我'
);