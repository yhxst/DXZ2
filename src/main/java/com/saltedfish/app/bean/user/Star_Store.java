package com.saltedfish.app.bean.user;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.Star_StoreMapper;

public class Star_Store extends BaseBean {
    public Star_Store(Star_StoreMapper star_StoreMapper){
        super(star_StoreMapper);
    }

    private String userId;
    private String postId;
    private boolean isStar;
    private boolean isStore;

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

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    public boolean isStore() {
        return isStore;
    }

    public void setStore(boolean store) {
        isStore = store;
    }
}
