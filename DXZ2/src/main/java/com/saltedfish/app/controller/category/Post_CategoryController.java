package com.saltedfish.app.controller.category;

import com.saltedfish.app.bean.category.Post_Category;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.mapper.Post_CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/category/post")
public class Post_CategoryController {
    @Autowired
    private Post_CategoryMapper mapper;


    /**
     * 通过 postId 获取到 post 的 Category
     * @param response
     * @param postId
     * @return
     */
    @RequestMapping("/get/{postId}")
    public Post_Category getAllCategory(HttpServletResponse response, @PathVariable("postId")String postId) {
        if (postId != null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
        }
        Post_Category category = mapper.getById(postId);
        return category;
    }

    @RequestMapping("/add")
    public void insert(Post_Category post_category) {
        post_category.init(mapper);
        mapper.insert(post_category);
    }

    @RequestMapping(value="/update")
    public void update(Post_Category post_category) {
        post_category.autoUpdateAt();
        mapper.update(post_category);
    }

    @RequestMapping(value="/delete/{code}")
    public void delete(@PathVariable("code") String code) {
        mapper.delete(code);
    }
}
