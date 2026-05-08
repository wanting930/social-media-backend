package com.esun.socialmediaplatform.service;

import com.esun.socialmediaplatform.dao.CommentDao;
import com.esun.socialmediaplatform.dao.PostDao;
import com.esun.socialmediaplatform.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public ObjectNode createComment(Integer userId, Integer postId, String json) throws Exception {
        JsonNode jsonNode = JsonUtils.getMapper().readTree(json);
        String content = jsonNode.get("content").asText();

        commentDao.createComment(userId, postId, content);

        ObjectNode result = JsonUtils.getMapper().createObjectNode();
        result.put("rtnCode", "0000");
        result.put("rtnMsg", "留言成功");
        return result;
    }
}
