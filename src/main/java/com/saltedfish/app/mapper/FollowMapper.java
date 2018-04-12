package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.file.FileUrl;
import com.saltedfish.app.bean.follow.Follow;
import com.saltedfish.app.bean.follow.FollowSum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import javax.management.modelmbean.ModelMBean;

public interface FollowMapper extends BaseMapper {
    @Select("select * from Follow where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into Follow(code,idolUserId,fansUserId,createAt,updateAt) values(#{code},#{idolUserId},#{fansUserId},#{createAt},#{updateAt})")
    void insert(Follow fileUrl);

    @Delete(value = "delete from Follow where code = #{code}")
    void delete(String code);

    /**
     * 删除关注关系
     * @param
     */
    @Delete(value = "delete from Follow where idolUserId = #{0} and fansUserId = #{1}")
    void deleteFollow(String  idol,String fans);

    @Delete(value = "delete from Follow where idolUserId is null or fansUserId is null ")
    void deleteFollowIsNull();

    @Update(value = "update Follow set idolUserId = #{idolUserId}, fansUserId = #{fansUserId},updateAt = #{updateAt} where code = #{code}")
    void update(Follow fileUrl);

    @Select(value = "select * from Follow where code = #{code}")
    Follow getOne(String code);

    @Select(value = "select * from Follow where fansUserId = #{0}")
    Model getIdol(String fansUserId);

    @Select(value = "select * from Follow where idolUserId = #{0}")
    Model getFans(String idolUserId);

    @Select(value = "select count(idolUserId) as idolSum from Follow where fansUserId = #{0} ")
    int getIdolSum(String fansUserId);

    @Select(value = "select count(fansUserId) as fansSum from Follow where idolUserId = #{0} ")
    int getFansSum(String idolUserId);

    //查询到是否关注
    @Select(value = "Select code from Follow where fansUserId = #{0} and idolUserId = #{1}")
    String getIsFollow(String fans,String idol);
}
