DROP PROCEDURE IF EXISTS sp_all_post;

DELIMITER $$

CREATE PROCEDURE sp_all_post()
BEGIN
    SELECT
        p.post_id,
        p.user_id,
        u.user_name,
        p.content,
        p.image,
        p.created_at,
        IFNULL(
            (SELECT JSON_ARRAYAGG(
                JSON_OBJECT(
                    'commentId', c.comment_id,
                    'author', cu.user_name,
                    'text', c.content,
                    'createdAt', c.created_at
                )
            )
            FROM comments c
            JOIN users cu
            ON c.user_id = cu.user_id
            WHERE c.post_id = p.post_id),
            JSON_ARRAY()
        ) AS comments
    FROM posts p
    JOIN users u
    ON p.user_id = u.user_id
    ORDER BY p.created_at DESC;
END $$

DELIMITER ;