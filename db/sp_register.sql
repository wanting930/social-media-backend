DROP PROCEDURE IF EXISTS sp_register;

DELIMITER $$

CREATE PROCEDURE sp_register(
    IN p_user_name VARCHAR(100),
    IN p_email VARCHAR(255),
    IN p_phone VARCHAR(20),
    IN p_password VARCHAR(255),
    IN p_cover_image LONGTEXT,
    IN p_biography TEXT
)
BEGIN

    INSERT INTO users (
        user_name,
        email,
        phone,
        password,
        cover_image,
        biography
    )
    VALUES (
        p_user_name,
        p_email,
        p_phone,
        p_password,
        p_cover_image,
        p_biography
    );

END $$

DELIMITER ;