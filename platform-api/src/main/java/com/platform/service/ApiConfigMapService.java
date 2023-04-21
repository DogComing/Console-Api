package com.platform.service;

import com.platform.dao.ApiConfigMapMapper;
import com.platform.entity.ConfigMapVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiConfigMapService {

    @Autowired
    private ApiConfigMapMapper mapMapper;

    /**
     * 查询某个地图详情
     * @param id
     * @return
     */
    public ConfigMapVo queryObject(Integer id) { return mapMapper.queryObject(id); }

    /**
     * 查询地图列表
     * @param map
     * @return
     */
    public List<ConfigMapVo> queryList(Map<String, Object> map) { return mapMapper.queryList(map); }

    /**
     * 获取地图总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return mapMapper.queryTotal(map); }

    /**
     * 新增地图
     * @param mapVo
     * @return
     */
    public int save(ConfigMapVo mapVo) { return mapMapper.save(mapVo); }

    /**
     * 更新地图信息
     * @param map
     * @return
     */
    public int update(Map<String, Object> map) { return mapMapper.update(map); }

    /**
     * 删除地图
     * @param id
     * @return
     */
    public int delete(Integer id) { return mapMapper.delete(id); }

    /**
     * 查询所有地图
     * @return
     */
    public List<ConfigMapVo> allMap() { return mapMapper.allMap(); }
}
