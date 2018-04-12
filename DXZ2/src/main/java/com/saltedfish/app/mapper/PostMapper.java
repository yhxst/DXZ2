package com.saltedfish.app.mapper;


import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.file.FileUrl;
import com.saltedfish.app.bean.post.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostMapper extends BaseMapper{

    @Select("select * from Post where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into Post(code,userId,address,content,createAt,updateAt) values(#{code},#{userId},#{address},#{content},#{createAt},#{updateAt})")
    void insert(Post post);

    @Delete(value = "delete from Post where code = #{0}")
    void delete(String postId);

    @Update(value = "update Post set heat = #{heat}, clickSum = #{clickSum},permission = #{permission},updateAt = #{updateAt} where code = #{code}")
    void update(Post post);

    @Update(value = "update Post set clickSum = clickSum +1 where code = #{0}")
    void updateClickSum(String postId);

    @Update(value = "update Post set permission  = #{permission},updateAt = #{updateAt} where code = #{0}")
    void updatePermission(String postId,int permission);

    @Select(value = "select * from Post where code = #{0}")
    Post getOne(String code);

    @Select(value = "select * from Post where code = #{0}")
    Post getOnePageByDesc(String post_categoryId);

    @Select(value = "select permission from Post where code = #{0}")
    int getPermission(String postId);

    @Select(value = "select userId from Post where code = #{0}")
    String getUserId(String postId);

    @Select("select code from Post where userId = #{0} and permission < #{1} order by createAt desc limit #{2},#{3}")
    List<String> getOnePageUserId(String userId,int permission,int star, int end);

    @Select(value = "select code from Post where content like CONCAT('%',#{0},'%') order by clickSum desc limit #{1},#{2}")
    List<String> getContentLike(String content,int star,int end);

    @Select(value = "select code from Post where content like CONCAT('%',#{0},'%') order by createAt desc limit #{1},#{2}")
    List<String> getContentLikeDesc(String content,int star,int end);

    @Select(value = "select code from Post where content like CONCAT('%',#{0},'%') order by createAt asc limit #{1},#{2}")
    List<String> getContentLikeAsc(String content,int star,int end);

    @Select(value = "select code from Post where content like CONCAT('%',#{0},'%') order by heat asc limit #{1},#{2}")
    List<String> getContentLikeHeat(String content,int star,int end);
}
