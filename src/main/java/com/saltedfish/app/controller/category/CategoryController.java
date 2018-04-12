package com.saltedfish.app.controller.category;


import com.saltedfish.app.bean.category.Category;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryMapper mapper;

    @RequestMapping("/get/{content}")
    public Category getOneByContent(HttpServletResponse response,@PathVariable("content")String content) {
        if (content != null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
        }
        Category category = mapper.getOne(content);
        return category;
    }

    @RequestMapping("/add")
    public void insert(Category category) {
        category.init(mapper);
        mapper.insert(category);
    }

    @RequestMapping(value="/update")
    public void update(Category category) {
        category.autoUpdateAt();
        mapper.update(category);
    }

    @RequestMapping(value="/delete/{code}")
    public void delete(@PathVariable("code") String code) {
        mapper.delete(code);
    }

}
