package com.platform.service;

import com.platform.dao.ApiConfigWildMapper;
import com.platform.entity.ConfigWildVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigWildService {

    @Autowired
    private ApiConfigWildMapper configWildMapper;

    /**
     * 查询某个野生详情
     * @param id
     * @return
     */
    public ConfigWildVo queryObject(Integer id) { return configWildMapper.queryObject(id); }

    /**
     * 根据条件查询野生列表
     * @param map
     * @return
     */
    public List<ConfigWildVo> queryList(Map<String, Object> map){ return configWildMapper.queryList(map); }

    /**
     * 获取野生总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return configWildMapper.queryTotal(map); }

    /**
     * 新增野生
     * @param wildVo
     * @return
     */
    public int save(ConfigWildVo wildVo) { return configWildMapper.save(wildVo); }

    /**
     * 更新野生信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return configWildMapper.update(map); }

    /**
     * 删除野生
     * @param id
     * @return
     */
    public int delete(Integer id) { return configWildMapper.delete(id); }

    /**
     * 查询所有野生
     * @return
     */
    public List<ConfigWildVo> allWild() { return configWildMapper.allWild(); }
}
