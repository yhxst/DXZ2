package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.comment.Comment;
import com.saltedfish.app.bean.file.FileUrl;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper{
    @Select("select * from Comment where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into Comment(code,userId,postId,content,createAt,updateAt) values(#{code},#{userId},#{postId},#{content},#{createAt},#{updateAt})")
    void insert(Comment fileUrl);

    @Delete(value = "delete from Comment where code = #{code}")
    void delete(String code);

    @Update(value = "update Comment set content = #{content},updateAt = #{updateAt} where code = #{code}")
    void update(Comment fileUrl);

    @Select(value = "select * from Comment where code = #{0}")
    Comment getOne(String code);

    /**
     * 取到博文当前页所有用户发的评论,时间从后到前
     * @param postId
     * @return
     */
    @Select(value = "select * from Comment where postId = #{0} Order by desc limit start,end")
    List<Comment> getCurrPageByDesc(String  postId,int start,int end);

    /**
     * 取到博文当前页所有用户发的评论,时间从前到后
     * @param postId
     * @return
     */
    @Select(value = "select * from Comment where postId = #{0} Order by asc limit start,end")
    List<Comment> getCurrPageByAsc(String  postId,int start,int end);
}
