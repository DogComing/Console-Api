package com.platform.service;

import com.platform.dao.ApiConfigCatchEquipMapper;
import com.platform.entity.ConfigCatchEquipVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigCatchEquipService {

    @Autowired
    private ApiConfigCatchEquipMapper configCatchEquipMapper;

    /**
     * 查询某个捕捉装备详情
     * @param id
     * @return
     */
    public ConfigCatchEquipVo queryObject(Integer id) { return configCatchEquipMapper.queryObject(id); }

    /**
     * 根据条件查询捕捉装备列表
     * @param map
     * @return
     */
    public List<ConfigCatchEquipVo> queryList(Map<String, Object> map){ return configCatchEquipMapper.queryList(map); }

    /**
     * 获取捕捉装备总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return configCatchEquipMapper.queryTotal(map); }

    /**
     * 新增捕捉装备
     * @param catchEquipVo
     * @return
     */
    public int save(ConfigCatchEquipVo catchEquipVo) { return configCatchEquipMapper.save(catchEquipVo); }

    /**
     * 更新捕捉装备信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return configCatchEquipMapper.update(map); }

    /**
     * 删除捕捉道具
     * @param id
     * @return
     */
    public int delete(Integer id) { return configCatchEquipMapper.delete(id); }

    /**
     * 查询所有捕捉装备
     * @return
     */
    public List<ConfigCatchEquipVo> allCatchEquip() { return configCatchEquipMapper.allCatchEquip(); }
}
