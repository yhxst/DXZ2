package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper {

    /**
     * 测试: 已通过
     * @param code
     * @return
     */
    @Select("select * from user where userCode = #{0}")
    @Override
    BaseBean getBean(String code);

    @Select("SELECT * FROM user")
    List<User> getAll();

    @Select("SELECT * FROM user WHERE userName = #{userName}")
    User getOne(String userName);

    @Select("SELECT userCode FROM user WHERE userPhone = #{0} and userPassWord = #{1}")
    String getUserCode(String userPhone,String userPassWord);

    @Select("SELECT * FROM user WHERE userPhone = #{0}")
    User getUserByPhone(String phone);

    @Select("SELECT * FROM user WHERE userName = #{0}")
    User getUserByName(String name);

    @Select("SELECT * FROM user WHERE userCode = #{0}")
    User getUserByCode(String code);

    @Select("SELECT * FROM user WHERE userPhone = #{0} and userPassWord = #{1}")
    User getUserCode1(String userPhone,String userPassWord);

    @Select("select userName from user where userCode = #{0}")
    String getUserName(String userId);

    @Select("select userPhone from user where userCode = #{0}")
    String getUserPhone(String userId);



    @Insert("INSERT INTO user(userCode,userName,userPassWord,userPhone,createAt,updateAt)" +
            " VALUES(#{userCode},#{userName}, #{userPassWord},#{userPhone},#{createAt},#{updateAt})")
    void insert(User user);

    @Update("UPDATE user SET userName=#{userName},userPassWord=#{userPassWord},userPhone = #{userPhone}, " +
            "updateAt = #{updateAt} WHERE userCode =#{userCode}")
    void update(User user);

    @Update("UPDATE user SET userName=#{1},updateAt = #{2} WHERE userCode =#{0}")
    void updateName(String userId, String name, Timestamp updateAt);

    @Update("UPDATE user SET userPhone=#{1},updateAt = #{2} WHERE userCode =#{0}")
    void updatePhone(String userId, String phone, Timestamp updateAt);


    @Delete("DELETE FROM user WHERE userCode =#{userCode}")
    void delete(String userCode);

    @Select("select * from user where userName like CONCAT('%',#{likeName},'%')  ")
    List<User> getLike(String likeName);

}