package com.saltedfish.app.controller.category;

import com.saltedfish.app.bean.category.User_Category;
import com.saltedfish.app.config.Info;
import com.saltedfish.app.config.ResponseInfo;
import com.saltedfish.app.mapper.User_CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/category/user")
public class User_CategoryController {
    @Autowired
    private User_CategoryMapper mapper;


    /**
     * 通过 userId 获取到 user 的 Category
     * @param response
     * @param userId
     * @return
     */
    @RequestMapping("/get/{userId}")
    public User_Category getAllCategory(HttpServletResponse response, @PathVariable("userId")String userId) {
        if (userId != null){
            ResponseInfo.setCode(response, Info.PARAM_ERR);
        }
        User_Category category = mapper.getById(userId);
        return category;
    }

    @RequestMapping("/add")
    public void insert(User_Category user_category) {
        user_category.init(mapper);
        mapper.insert(user_category);
    }

    @RequestMapping(value="/update")
    public void update(User_Category user_category) {
        user_category.autoUpdateAt();
        mapper.update(user_category);
    }

    @RequestMapping(value="/delete/{code}")
    public void delete(@PathVariable("code") String code) {
        mapper.delete(code);
    }
}
