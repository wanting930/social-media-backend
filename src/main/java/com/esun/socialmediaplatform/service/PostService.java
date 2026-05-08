package com.esun.socialmediaplatform.service;

import com.esun.socialmediaplatform.dao.PostDao;
import com.esun.socialmediaplatform.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    public ObjectNode getAllPosts() throws Exception {
        List<Map<String, Object>> posts = postDao.getAllPosts();

        ArrayNode data = JsonUtils.getMapper().createArrayNode();

        for (Map<String, Object> post : posts) {
            ObjectNode item = JsonUtils.getMapper().createObjectNode();
            item.put("postId", post.get("post_id").toString());
            item.put("userId", post.get("user_id").toString());
            item.put("userName", post.get("user_name").toString());
            item.put("content", post.get("content").toString());

            if (post.get("image") != null) {
                item.put("image", post.get("image").toString());
            } else {
                item.putNull("image");
            }

            item.put("createdAt", post.get("created_at").toString());

            data.add(item);
        }

        ObjectNode result = JsonUtils.getMapper().createObjectNode();
        result.put("rtnCode", "0000");
        result.put("rtnMsg", "查詢成功");
        result.set("data", data);
        return result;
    }

    public ObjectNode createPost(Integer userId, String json) throws Exception {
        JsonNode jsonNode = JsonUtils.getMapper().readTree(json);
        String content = jsonNode.get("content").asText();
        String image = jsonNode.hasNonNull("image") ? jsonNode.get("image").asText() : null;

        postDao.createPost(userId, content, image);

        ObjectNode result = JsonUtils.getMapper().createObjectNode();
        result.put("rtnCode", "0000");
        result.put("rtnMsg", "發文成功");
        return result;
    }

    public ObjectNode updatePost(Integer postId, Integer userId, String json) throws Exception {
        JsonNode jsonNode = JsonUtils.getMapper().readTree(json);
        String content = jsonNode.get("content").asText();
        String image = jsonNode.hasNonNull("image") ? jsonNode.get("image").asText() : null;

        int rows = postDao.updatePost(postId, userId, content, image);

        ObjectNode result = JsonUtils.getMapper().createObjectNode();

        if (rows == 0) {
            result.put("rtnCode", "9998");
            result.put("rtnMsg", "文章不存在或無權限編輯");
            return result;
        }

        result.put("rtnCode", "0000");
        result.put("rtnMsg", "文章編輯成功");
        return result;
    }

    public ObjectNode deletePost(Integer postId, Integer userId) throws Exception {

        int rows = postDao.deletePost(postId, userId);

        ObjectNode result = JsonUtils.getMapper().createObjectNode();

        if (rows == 0) {
            result.put("rtnCode", "9998");
            result.put("rtnMsg", "文章不存在或無權限刪除");
            return result;
        }

        result.put("rtnCode", "0000");
        result.put("rtnMsg", "文章刪除成功");
        return result;
    }
}
