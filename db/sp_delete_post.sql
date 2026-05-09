DROP PROCEDURE IF EXISTS sp_delete_post;

DELIMITER $$

CREATE PROCEDURE sp_delete_post(
    IN p_post_id INT,
    IN p_user_id INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 0 AS result;
    END;

    START TRANSACTION;

    SELECT COUNT(*)
    INTO v_count
    FROM posts
    WHERE post_id = p_post_id
    AND user_id = p_user_id;

    IF v_count = 0 THEN
        ROLLBACK;
        SELECT 0 AS result;
    ELSE
        DELETE FROM comments
        WHERE post_id = p_post_id;

        DELETE FROM posts
        WHERE post_id = p_post_id
        AND user_id = p_user_id;

        COMMIT;

        SELECT 1 AS result;
    END IF;
END $$

DELIMITER ;