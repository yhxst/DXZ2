package com.saltedfish.app.mapper;

import com.saltedfish.app.bean.BaseBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BaseMapper {

    BaseBean getBean(String code);
}
