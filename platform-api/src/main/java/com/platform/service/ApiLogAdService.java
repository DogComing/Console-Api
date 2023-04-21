package com.platform.service;

import com.platform.dao.ApiLogAdMapper;
import com.platform.entity.LogAdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiLogAdService {

    @Autowired
    private ApiLogAdMapper logAdMapper;

    /**
     * 查询某条广告观看记录详情
     * @param id
     * @return
     */
    public LogAdVo queryObject(Integer id) { return logAdMapper.queryObject(id); }

    /**
     * 根据条件查询广告观看记录列表（每日凌晨清空昨天记录）
     * @param map
     * @return
     */
    public List<LogAdVo> queryList(Map<String, Object> map){ return logAdMapper.queryList(map); }

    /**
     * 获取广告观看记录总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return logAdMapper.queryTotal(map); }

    /**
     * 删除广告观看记录
     * @param id
     * @return
     */
    public int delete(Integer id) { return logAdMapper.delete(id); }
}
