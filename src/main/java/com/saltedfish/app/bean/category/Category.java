package com.saltedfish.app.bean.category;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.mapper.CategoryMapper;

public class Category extends BaseBean {
    public Category(CategoryMapper mapper){
        super(mapper);
    }
    private String userId;
    private String content;
    private double heat;
    private int clickSum;
}
