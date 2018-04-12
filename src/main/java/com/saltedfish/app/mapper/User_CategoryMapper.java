package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.category.User_Category;
import com.saltedfish.app.bean.file.FileUrl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface User_CategoryMapper extends BaseMapper{

    @Select("select * from User_Category where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into User_Category(code,userId,categoryId,clickSum,createAt,updateAt) values(#{code},#{userId},#{categoryId},#{clickSum},#{createAt},#{updateAt})")
    void insert(User_Category user_category);

    @Delete(value = "delete from User_Category where code = #{0}")
    void delete(String code);

    @Update(value = "update User_Category set clickSum = #{clickSum},updateAt = #{updateAt} where code = #{code}")
    void update(User_Category fileUrl);

    @Select(value = "select * from User_Category where code = #{0}")
    User_Category getById(String code);

    @Select(value = "select * from User_Category where userId = #{0}")
    User_Category getByUserId(String userId);
}
