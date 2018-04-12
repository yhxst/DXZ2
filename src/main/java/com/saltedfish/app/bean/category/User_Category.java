package com.saltedfish.app.bean.category;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.User_CategoryMapper;

public class User_Category extends BaseBean {
    public User_Category(User_CategoryMapper mapper){
        super(mapper);
    }

    private String userId;
    private String categoryId;
    private int clickSum;
}
