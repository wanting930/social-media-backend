package com.esun.socialmediaplatform.controller;

import com.esun.socialmediaplatform.service.UserService;
import com.esun.socialmediaplatform.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.node.ObjectNode;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String json) {
        try {
            ObjectNode result = userService.register(json);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.put("rtnCode", "9999");
            objectNode.put("rtnMsg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode.toString());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String json) {
        try {
            ObjectNode result = userService.login(json);
            return ResponseEntity.ok(result.toString());
        } catch (RuntimeException e) {
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.put("rtnCode", "9999");
            objectNode.put("rtnMsg", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(objectNode.toString());
        } catch (Exception e) {
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.put("rtnCode", "9999");
            objectNode.put("rtnMsg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode.toString());
        }
    }
}
