package com.platform.service;

import com.platform.dao.ApiConfigFightEquipMapper;
import com.platform.entity.ConfigFightEquipVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigFightEquipService {

    @Autowired
    private ApiConfigFightEquipMapper configFightEquipMapper;

    /**
     * 查询某个对战装备详情
     * @param id
     * @return
     */
    public ConfigFightEquipVo queryObject(Integer id) { return configFightEquipMapper.queryObject(id); }

    /**
     * 根据条件查询对战装备列表
     * @param map
     * @return
     */
    public List<ConfigFightEquipVo> queryList(Map<String, Object> map){ return configFightEquipMapper.queryList(map); }

    /**
     * 获取对战装备总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return configFightEquipMapper.queryTotal(map); }

    /**
     * 新增对战装备
     * @param fightEquipVo
     * @return
     */
    public int save(ConfigFightEquipVo fightEquipVo) { return configFightEquipMapper.save(fightEquipVo); }

    /**
     * 更新对战道具信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return configFightEquipMapper.update(map); }

    /**
     * 删除对战道具
     * @param id
     * @return
     */
    public int delete(Integer id) { return configFightEquipMapper.delete(id); }

    /**
     * 查询所有对战装备
     * @return
     */
    public List<ConfigFightEquipVo> allFightEquip() { return configFightEquipMapper.allFightEquip(); }
}
