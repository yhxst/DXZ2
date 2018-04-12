package com.saltedfish.app.bean.post;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.PostMapper;

public class Post extends BaseBean {
    public Post(){}
    public Post(PostMapper mapper){
        super(mapper);
    }

    private String userId;
    private String address;
    private String content;
    private double heat;
    private int clickSum;
    private int permission;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getHeat() {
        return heat;
    }

    public void setHeat(double heat) {
        this.heat = heat;
    }

    public int getClickSum() {
        return clickSum;
    }

    public void setClickSum(int clickSum) {
        this.clickSum = clickSum;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
