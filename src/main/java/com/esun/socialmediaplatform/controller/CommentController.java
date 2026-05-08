package com.esun.socialmediaplatform.controller;

import com.esun.socialmediaplatform.service.CommentService;
import com.esun.socialmediaplatform.utils.JsonUtils;
import com.esun.socialmediaplatform.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.node.ObjectNode;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<String> createComment(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Integer postId,
            @RequestBody String json) {
        try {
            String token = authorization.replace("Bearer ", "");
            Integer userId = JwtUtils.getUserIdFromToken(token);

            ObjectNode result = commentService.createComment(userId, postId, json);
            return ResponseEntity.ok(result.toString());
        } catch (Exception e) {
            ObjectNode objectNode = JsonUtils.getMapper().createObjectNode();
            objectNode.put("rtnCode", "9999");
            objectNode.put("rtnMsg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectNode.toString());
        }
    }
}
