DROP PROCEDURE IF EXISTS sp_update_post;

DELIMITER $$

CREATE PROCEDURE sp_update_post(
    IN p_post_id INT,
    IN p_user_id INT,
    IN p_content TEXT,
    IN p_image LONGTEXT
)
BEGIN
    UPDATE posts
    SET content = p_content,
        image = p_image
    WHERE post_id = p_post_id
    AND user_id = p_user_id;
END $$

DELIMITER ;