package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.category.Category;
import com.saltedfish.app.bean.file.FileUrl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryMapper extends BaseMapper {
    @Select("select * from Category where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into Category(code,content,heat,clickSum,createAt,updateAt) values(#{code},#{content},#{heat},#{clickSum},#{createAt},#{updateAt})")
    void insert(Category category);

    @Delete(value = "delete from Category where code = #{0}")
    void delete(String code);

    @Update(value = "update Category set content = #{content}, heat = #{heat},clickSum = #{clickSum},updateAt = #{updateAt} where code = #{code}")
    void update(Category category);

    @Select(value = "select * from Category where code = #{0}")
    Category getOne(String code);

    //取得包含指定 content 的 categoryId
    @Select(value = "select code from Category where content = #{0}")
    String getCategoryIdByContent(String content);
}
