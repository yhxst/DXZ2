package com.saltedfish.app.bean.file;


import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.FileUrlMapper;

public class FileUrl extends BaseBean {

    private String userId;
    private String url;
    private boolean permission;
    private String postId;


    public FileUrl(){}
    public FileUrl(FileUrlMapper fileUrlMapper){
        super(fileUrlMapper);
        permission = true;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
