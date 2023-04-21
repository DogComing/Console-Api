package com.platform.service;

import com.platform.dao.ApiLogRakeBackMapper;
import com.platform.entity.LogRakeBackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiLogRakeBackService {

    @Autowired
    private ApiLogRakeBackMapper logRakeBackMapper;

    /**
     * 查询某条返佣记录详情
     * @param id
     * @return
     */
    public LogRakeBackVo queryObject(Integer id) { return logRakeBackMapper.queryObject(id); }

    /**
     * 根据条件查询对返佣记录列表
     * @param map
     * @return
     */
    public List<LogRakeBackVo> queryList(Map<String, Object> map){ return logRakeBackMapper.queryList(map); }

    /**
     * 获取返佣记录总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return logRakeBackMapper.queryTotal(map); }

    /**
     * 删除返佣记录
     * @param id
     * @return
     */
    public int delete(Integer id) { return logRakeBackMapper.delete(id); }
}
