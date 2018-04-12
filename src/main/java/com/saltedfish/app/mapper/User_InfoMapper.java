package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.file.FileUrl;
import com.saltedfish.app.bean.user.User_Info;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

public interface User_InfoMapper extends BaseMapper{

    @Select("select * from User_Info where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into User_Info(code,userId,sex,headPhoto,describes,email,createAt,updateAt) values(#{code},#{userId},#{sex},#{headPhoto},#{describes},#{email},#{createAt},#{updateAt})")
    void insert(User_Info user_Info);

    @Delete(value = "delete from User_Info where code = #{code}")
    void delete(String code);

    @Update(value = "update User_Info set sex = #{sex},describes = #{describes},email = #{email},updateAt = #{updateAt} where code = #{code}")
    void update(User_Info user_Info);

    @Update(value = "update User_Info set headPhoto = #{1},updateAt = #{2} where userId = #{0}")
    void updateUserHeadPhoto(String userId, String headPhoto, Timestamp updateAt);

    @Select(value = "select * from User_Info where userId = #{0}")
    User_Info getOne(String userId);

    @Select(value = "select code from User_Info where userId = #{0}")
    String getUserInfoId(String userId);

    @Select(value = "select headPhoto from User_Info where userId = #{0}")
    String getUserHeadPhotoUrl(String userId);

}
