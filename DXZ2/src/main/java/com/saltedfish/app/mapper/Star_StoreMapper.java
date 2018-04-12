package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.file.FileUrl;
import com.saltedfish.app.bean.user.Star_Store;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface Star_StoreMapper extends BaseMapper{

    @Select("select * from Star_Store where code = #{0}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into Star_Store(code,userId,postId,isStar,isStore,createAt,updateAt) values(#{code},#{userId},#{postId},#{isStar},#{isStore},#{createAt},#{updateAt})")
    void insert(Star_Store star_Store);

    @Delete(value = "delete from Star_Store where code = #{code}")
    void delete(String code);

    @Update(value = "update Star_Store set isStar = #{isStar}, isStore = #{isStore},updateAt = #{updateAt} where code = #{code}")
    void update(Star_Store star_Store);

    @Select(value = "select * from Star_Store where code = #{0}")
    Star_Store getOne(String code);

    @Select(value = "select count(*) from Star_Store where postId = #{0} and isStar = true")
    int getStarSumByPostId(String postId);
}
