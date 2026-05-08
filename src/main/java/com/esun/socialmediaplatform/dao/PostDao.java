package com.esun.socialmediaplatform.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PostDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Map<String, Object>> getAllPosts() {
        String sp = "CALL sp_all_post()";

        return namedParameterJdbcTemplate.queryForList(sp, new MapSqlParameterSource());
    }

    public void createPost(
            Integer userId,
            String content,
            String image
    ) {
        String sp = "CALL sp_create_post(:userId, :content, :image)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        param.addValue("content", content);
        param.addValue("image", image);

        namedParameterJdbcTemplate.update(sp, param);
    }

    public int updatePost(
            Integer postId,
            Integer userId,
            String content,
            String image
    ) {
        String sp = "CALL sp_update_post(:postId, :userId, :content, :image)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("postId", postId);
        param.addValue("userId", userId);
        param.addValue("content", content);
        param.addValue("image", image);

        return namedParameterJdbcTemplate.update(sp, param);
    }

    public int deletePost(Integer postId, Integer userId) {
        String sp = "CALL sp_delete_post(:postId, :userId)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("postId", postId);
        param.addValue("userId", userId);

        return namedParameterJdbcTemplate.update(sp, param);
    }
}
