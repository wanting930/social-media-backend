DROP PROCEDURE IF EXISTS sp_create_post;

DELIMITER $$

CREATE PROCEDURE sp_create_post(
    IN p_user_id INT,
    IN p_content TEXT,
    IN p_image LONGTEXT
)
BEGIN

    INSERT INTO posts (
        user_id,
        content,
        image
    )
    VALUES (
        p_user_id,
        p_content,
        p_image
    );

END $$

DELIMITER ;