package com.platform.dao;

import com.platform.entity.ConfigDogVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiConfigDogMapper extends BaseDao<ConfigDogVo>{

    List<ConfigDogVo> allDog();
}
