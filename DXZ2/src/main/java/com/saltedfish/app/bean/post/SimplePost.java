package com.saltedfish.app.bean.post;

import java.util.List;

public class SimplePost {
    private String code;
    private String userId;//发博 code
    private String userName;
    private String content;
    private List<String> photoUrls;//照片的 url
    private String userHeadPhotoUrl;//发博用户的头像
    private String address;
    private int starSum;//这篇博被赞的数量
    private int permission;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public String getUserHeadPhotoUrl() {
        return userHeadPhotoUrl;
    }

    public void setUserHeadPhotoUrl(String userHeadPhotoUrl) {
        this.userHeadPhotoUrl = userHeadPhotoUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStarSum() {
        return starSum;
    }

    public void setStarSum(int starSum) {
        this.starSum = starSum;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
