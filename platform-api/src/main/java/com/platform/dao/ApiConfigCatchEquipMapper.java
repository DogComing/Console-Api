package com.platform.dao;

import com.platform.entity.ConfigCatchEquipVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiConfigCatchEquipMapper extends BaseDao<ConfigCatchEquipVo>{

    List<ConfigCatchEquipVo> allCatchEquip();
}
