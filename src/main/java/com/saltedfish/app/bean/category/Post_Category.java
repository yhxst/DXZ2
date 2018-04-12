package com.saltedfish.app.bean.category;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.Post_CategoryMapper;

public class Post_Category extends BaseBean {
    public Post_Category(Post_CategoryMapper mapper){
        super(mapper);
    }
    private String postId;
    private String categoryId;
    private int clickSum;
}
