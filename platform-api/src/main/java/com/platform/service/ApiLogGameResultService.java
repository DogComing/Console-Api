package com.platform.service;

import com.platform.dao.ApiLogGameResultMapper;
import com.platform.entity.LogGameResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiLogGameResultService {

    @Autowired
    private ApiLogGameResultMapper gameResultMapper;

    /**
     * 查询某场比赛结果记录详情
     * @param id
     * @return
     */
    public LogGameResultVo queryObject(Integer id) { return gameResultMapper.queryObject(id); }

    /**
     * 根据条件查询比赛结果记录列表
     * @param map
     * @return
     */
    public List<LogGameResultVo> queryList(Map<String, Object> map){ return gameResultMapper.queryList(map); }

    /**
     * 获取比赛结果记录总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return gameResultMapper.queryTotal(map); }

    /**
     * 删除比赛结果记录
     * @param id
     * @return
     */
    public int delete(Integer id) { return gameResultMapper.delete(id); }

}
