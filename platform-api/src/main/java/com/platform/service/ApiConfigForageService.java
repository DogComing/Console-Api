package com.platform.service;

import com.platform.dao.ApiConfigForageMapper;
import com.platform.entity.ConfigForageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigForageService {

    @Autowired
    private ApiConfigForageMapper configForageMapper;

    /**
     * 查询某个饲料详情
     * @param id
     * @return
     */
    public ConfigForageVo queryObject(Integer id) { return configForageMapper.queryObject(id); }

    /**
     * 根据条件查询饲料列表
     * @param map
     * @return
     */
    public List<ConfigForageVo> queryList(Map<String, Object> map){ return configForageMapper.queryList(map); }

    /**
     * 获取饲料总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return configForageMapper.queryTotal(map); }

    /**
     * 新增饲料
     * @param forageVo
     * @return
     */
    public int save(ConfigForageVo forageVo) { return configForageMapper.save(forageVo); }

    /**
     * 更新饲料信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return configForageMapper.update(map); }

    /**
     * 删除饲料
     * @param id
     * @return
     */
    public int delete(Integer id) { return configForageMapper.delete(id); }

    /**
     * 查询所有饲料
     * @return
     */
    public List<ConfigForageVo> allForage() { return configForageMapper.allForage(); }
}
