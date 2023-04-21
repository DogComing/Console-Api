package com.platform.service;

import com.platform.dao.ApiConfigDogMapper;
import com.platform.entity.ConfigDogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigDogService {

    @Autowired
    private ApiConfigDogMapper configDogMapper;

    /**
     * 查询配置表狗狗详情
     * @param id
     * @return
     */
    public ConfigDogVo queryObject(Integer id) { return configDogMapper.queryObject(id); }

    /**
     * 根据条件查询狗狗列表
     * @param map
     * @return
     */
    public List<ConfigDogVo> queryList(Map<String, Object> map){ return configDogMapper.queryList(map); }

    /**
     * 获取狗狗总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return configDogMapper.queryTotal(map); }

    /**
     * 新增狗狗
     * @param dogVo
     * @return
     */
    public int save(ConfigDogVo dogVo) { return configDogMapper.save(dogVo); }

    /**
     * 更新狗狗信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return configDogMapper.update(map); }

    /**
     * 删除狗狗
     * @param id
     * @return
     */
    public int delete(Integer id) { return configDogMapper.delete(id); }

    /**
     * 查询配置表所有狗狗
     * @return
     */
    public List<ConfigDogVo> allDog() { return configDogMapper.allDog(); }
}
