package com.platform.service;

import com.platform.dao.ApiConfigAdMapper;
import com.platform.entity.ConfigAdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigAdService {

    @Autowired
    private ApiConfigAdMapper configAdMapper;

    /**
     * 查询配置表广告详情
     * @param id
     * @return
     */
    public ConfigAdVo queryObject(Integer id) { return configAdMapper.queryObject(id); }

    /**
     * 根据条件查询广告列表
     * @param map
     * @return
     */
    public List<ConfigAdVo> queryList(Map<String, Object> map){ return configAdMapper.queryList(map); }

    /**
     * 获取广告总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return configAdMapper.queryTotal(map); }

    /**
     * 新增广告
     * @param adVo
     * @return
     */
    public int save(ConfigAdVo adVo) { return configAdMapper.save(adVo); }

    /**
     * 更新广告信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return configAdMapper.update(map); }

    /**
     * 删除广告
     * @param id
     * @return
     */
    public int delete(Integer id) { return configAdMapper.delete(id); }

}
