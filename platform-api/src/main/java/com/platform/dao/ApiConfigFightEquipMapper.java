package com.platform.dao;

import com.platform.entity.ConfigFightEquipVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiConfigFightEquipMapper extends BaseDao<ConfigFightEquipVo>{

    List<ConfigFightEquipVo> allFightEquip();
}
