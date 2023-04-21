package com.platform.dao;

import com.platform.entity.SysTokenVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: platform
 * @description: 用户Token
 * @author: Yuan
 * @create: 2020-09-09 10:20
 **/
@Mapper
public interface SysTokenMapper extends BaseDao<SysTokenVo> {

    SysTokenVo queryByUserId(@Param("userId") Long userId);

    SysTokenVo queryByToken(@Param("token") String token);

}
