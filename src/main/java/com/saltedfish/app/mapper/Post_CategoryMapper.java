package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.category.Category;
import com.saltedfish.app.bean.category.Post_Category;
import com.saltedfish.app.bean.file.FileUrl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface Post_CategoryMapper extends BaseMapper {

    @Select("select * from Post_Category where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into Post_Category(code,postId,categoryId,clickSum,createAt,updateAt) values(#{userId},#{file},#{permission},#{code},#{createAt},#{updateAt})")
    void insert(Post_Category post_Category);

    @Delete(value = "delete from Post_Category where code = #{code}")
    void delete(String code);

    @Update(value = "update Post_Category set categoryId = #{categoryId}, clickSum = #{clickSum},updateAt = #{updateAt} where code = #{code}")
    void update(Post_Category post_Category);

    @Select(value = "select * from Post_Category where postId = #{0}")
    Post_Category getById(String postId);

    @Select(value = "select postId from Post_Category where categoryId = #{0} order by clickSum limit #{1},#{2}")
    List<String> getPostIdsByCategoryId(String categoryId,int star,int end);
}
