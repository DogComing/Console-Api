package com.platform.dao;

import com.platform.entity.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiUserMapper extends BaseDao<UserVo> {

}
