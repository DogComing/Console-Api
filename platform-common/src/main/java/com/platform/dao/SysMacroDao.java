package com.platform.dao;

import com.platform.entity.SysMacroEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用字典表Dao
 *
 * @author xingGuangTeam
 * @email 249893127@qq.com
 * @date 2017-08-22 11:48:16
 */
@Mapper
public interface SysMacroDao extends BaseDao<SysMacroEntity> {

    /**
     * 查询数据字段
     *
     * @param value
     * @return
     */
    List<SysMacroEntity> queryMacrosByValue(@Param("value") String value);
}
