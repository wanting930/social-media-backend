package com.esun.socialmediaplatform.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createComment(
            Integer userId,
            Integer postId,
            String content
    ) {
        String sp = "CALL sp_create_comment(:userId, :postId, :content)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        param.addValue("postId", postId);
        param.addValue("content", content);

        namedParameterJdbcTemplate.update(sp, param);
    }

}
