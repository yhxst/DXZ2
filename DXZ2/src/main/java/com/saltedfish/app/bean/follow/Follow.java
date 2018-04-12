package com.saltedfish.app.bean.follow;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.FollowMapper;

public class Follow extends BaseBean {
    public Follow(){}
    public Follow(FollowMapper mapper){
        super(mapper);
    }
    private String idolUserId;
    private String fansUserId;

    public String getIdolUserId() {
        return idolUserId;
    }

    public void setIdolUserId(String idolUserId) {
        this.idolUserId = idolUserId;
    }

    public String getFansUserId() {
        return fansUserId;
    }

    public void setFansUserId(String fansUserId) {
        this.fansUserId = fansUserId;
    }
}
