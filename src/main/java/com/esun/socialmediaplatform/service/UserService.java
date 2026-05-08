package com.esun.socialmediaplatform.service;

import com.esun.socialmediaplatform.dao.UserDao;
import com.esun.socialmediaplatform.utils.JsonUtils;
import com.esun.socialmediaplatform.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ObjectNode register(String json) throws Exception {
        JsonNode jsonNode = JsonUtils.getMapper().readTree(json);
        String userName = jsonNode.get("userName").asText();
        String email = jsonNode.get("email").asText();
        String phone = jsonNode.get("phone").asText();
        String password = jsonNode.get("password").asText();
        String encodedPassword = passwordEncoder.encode(password);
        String coverImage = jsonNode.hasNonNull("coverImage") ? jsonNode.get("coverImage").asText() : null;
        String biography = jsonNode.hasNonNull("biography") ? jsonNode.get("biography").asText() : null;

        userDao.register(
                userName,
                email,
                phone,
                encodedPassword,
                coverImage,
                biography
        );

        ObjectNode result = JsonUtils.getMapper().createObjectNode();
        result.put("rtnCode", "0000");
        result.put("rtnMsg", "註冊成功");
        return result;
    }

    public ObjectNode login(String json) throws Exception {
        JsonNode jsonNode = JsonUtils.getMapper().readTree(json);
        String phone = jsonNode.get("phone").asText();
        String password = jsonNode.get("password").asText();

        Map<String, Object> user = userDao.login(phone);

        String encodedPassword = user.get("password").toString();
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new RuntimeException("帳號或密碼錯誤");
        }

        Integer userId = Integer.parseInt(user.get("user_id").toString());
        String token = JwtUtils.generateToken(userId, phone);

        ObjectNode data = JsonUtils.getMapper().createObjectNode();
        data.put("userId", user.get("user_id").toString());
        data.put("userName", user.get("user_name").toString());
        data.put("email", user.get("email").toString());
        data.put("phone", user.get("phone").toString());
        if (user.get("cover_image") != null) {
            data.put("coverImage", user.get("cover_image").toString());
        } else {
            data.putNull("coverImage");
        }
        if (user.get("biography") != null) {
            data.put("biography", user.get("biography").toString());
        } else {
            data.putNull("biography");
        }
        data.put("token", token);

        ObjectNode result = JsonUtils.getMapper().createObjectNode();
        result.put("rtnCode", "0000");
        result.put("rtnMsg", "登入成功");
        result.set("data", data);
        return result;
    }
}
