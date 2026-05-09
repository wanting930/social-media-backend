DROP PROCEDURE IF EXISTS sp_login;

DELIMITER $$

CREATE PROCEDURE sp_login(
    IN p_phone VARCHAR(20)
)
BEGIN
    SELECT
        user_id,
        user_name,
        email,
        phone,
        password,
        cover_image,
        biography
    FROM users
    WHERE phone = p_phone;
END $$

DELIMITER ;