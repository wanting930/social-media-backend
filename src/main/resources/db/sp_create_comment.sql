DROP PROCEDURE IF EXISTS sp_create_comment;

DELIMITER $$

CREATE PROCEDURE sp_create_comment(
    IN p_user_id INT,
    IN p_post_id INT,
    IN p_content TEXT
)
BEGIN
    INSERT INTO comments (
        user_id,
        post_id,
        content
    )
    VALUES (
        p_user_id,
        p_post_id,
        p_content
    );
END $$

DELIMITER ;