package com.platform.service;

import com.platform.dao.ApiConfigPropMapper;
import com.platform.entity.ConfigPropVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigPropService {

    @Autowired
    private ApiConfigPropMapper configPropMapper;

    /**
     * 查询某个道具详情
     * @param id
     * @return
     */
    public ConfigPropVo queryObject(Integer id) { return configPropMapper.queryObject(id); }

    /**
     * 根据条件查询道具列表
     * @param map
     * @return
     */
    public List<ConfigPropVo> queryList(Map<String, Object> map){ return configPropMapper.queryList(map); }

    /**
     * 获取道具总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return configPropMapper.queryTotal(map); }

    /**
     * 新增道具
     * @param propVo
     * @return
     */
    public int save(ConfigPropVo propVo) { return configPropMapper.save(propVo); }

    /**
     * 更新道具信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return configPropMapper.update(map); }

    /**
     * 删除道具
     * @param id
     * @return
     */
    public int delete(Integer id) { return configPropMapper.delete(id); }

    /**
     * 查询所有道具
     * @return
     */
    public List<ConfigPropVo> allProp() { return configPropMapper.allProp(); }
}
