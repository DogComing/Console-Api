package com.platform.dao;

import com.platform.entity.ConfigForageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiConfigForageMapper extends BaseDao<ConfigForageVo>{

    List<ConfigForageVo> allForage();
}
