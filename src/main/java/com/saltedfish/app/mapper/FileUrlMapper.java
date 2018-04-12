package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import com.saltedfish.app.bean.file.FileUrl;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileUrlMapper extends BaseMapper{

    /**
     * 测试: 已通过
     * @param code
     * @return
     */
    @Select("select * from fileUrl where code = #{code}")
    @Override
    BaseBean getBean(String code);

    @Insert(value = "insert into fileUrl(userId,url,permission,code,createAt,updateAt,postId) values(#{userId},#{url},#{permission},#{code},#{createAt},#{updateAt},#{postId})")
    void insert(FileUrl fileUrl);

    @Delete(value = "delete from fileUrl where code = #{code}")
    void delete(String code);

    @Update(value = "update fileUrl set file = #{file}, permission = #{permission},userId = #{userId},updateAt = #{updateAt} where code = #{code}")
    void update(FileUrl fileUrl);

    @Select(value = "select * from fileUrl where code = #{code}")
    FileUrl getOne(String code);

    /**
     * 按照时间从后到前
     * @param userId
     * @param start
     * @param end
     * @return
     */
    @Select(value = "select * from fileUrl where userId = #{0} order by createAt desc limit #{1},#{2}")
    List<FileUrl> getCurrPageByDesc(String userId, int start, int end);

    /**
     * 按照时间从前到后
     * @param userId
     * @param start
     * @param end
     * @return
     */
    @Select(value = "select * from fileUrl where userId = #{0} order by createAt asc limit #{1},#{2}")
    List<FileUrl> getCurrPageByAsc(String userId, int start, int end);

    /**
     *
     * @param userId
     * @param number 每页的个数
     * @return
     */
    @Select(value = "select count(*)/#{1} from fileUrl where userId = #{0}")
    int getAllPage(String userId,int number);

    @Select(value = "Select url from fileUrl where postId = #{0}")
    List<String> getFileUrlByPostId(String postId);
}
