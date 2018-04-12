package com.saltedfish.app.controller;

import java.util.List;

import com.saltedfish.app.bean.User;
import com.saltedfish.app.intercept.Auth;
import com.saltedfish.app.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getOne")
    public List<User> getUsers() {
        List<User> users=userMapper.getAll();
        return users;
    }

    @RequestMapping("/getOne/{name}")
    public User getUser(@PathVariable("userName")String userName) {
        User user=userMapper.getOne(userName);
        return user;
    }

    @RequestMapping("/add")
    public void save(User user) {
        user.autoUserCode(userMapper);
        user.autoCreateAt();
        user.autoUpdateAt();
        userMapper.insert(user);
    }

    @RequestMapping(value="/update")
    public void update(User user) {
        user.autoUpdateAt();
        userMapper.update(user);
    }

    @RequestMapping(value="/delete/{userCode}")
    public void delete(@PathVariable("userCode") String userCode) {
        userMapper.delete(userCode);
    }

    //模糊查询
    @RequestMapping(value = "/getLikeName/{likeName}")
    public List<User> getLikeName(@PathVariable("likeName")String likeName){
        System.out.println(likeName);
        return userMapper.getLike(likeName);
    }

}