package com.esun.socialmediaplatform.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void register(
            String userName,
            String email,
            String phone,
            String encodedPassword,
            String coverImage,
            String biography
    ) {
        String sp = "CALL sp_register(:userName, :email, :phone, :password, :coverImage, :biography)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userName", userName);
        param.addValue("email", email);
        param.addValue("phone", phone);
        param.addValue("password", encodedPassword);
        param.addValue("coverImage", coverImage);
        param.addValue("biography", biography);

        namedParameterJdbcTemplate.update(sp, param);
    }

    public Map<String, Object> login(String phone) {
        String sp = "CALL sp_login(:phone)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("phone", phone);

        return namedParameterJdbcTemplate.queryForMap(sp, param);
    }
}
