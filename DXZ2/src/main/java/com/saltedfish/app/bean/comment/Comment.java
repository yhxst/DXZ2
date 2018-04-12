package com.saltedfish.app.bean.comment;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.CommentMapper;

public class Comment extends BaseBean {
    public Comment(CommentMapper mapper){
        super(mapper);
    }

    private String userId;
    private String postId;
    private String content;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
