package com.platform.service;

import com.platform.dao.ApiLogASGMapper;
import com.platform.entity.LogASGVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiLogASGService {

    @Autowired
    private ApiLogASGMapper logMapper;

    /**
     * 查询某条ASG记录详情
     * @param id
     * @return
     */
    public LogASGVo queryObject(Integer id) { return logMapper.queryObject(id); }

    /**
     * 根据条件查询ASG记录列表
     * @param map
     * @return
     */
    public List<LogASGVo> queryList(Map<String, Object> map){ return logMapper.queryList(map); }

    /**
     * 获取ASG记录总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return logMapper.queryTotal(map); }

    /**
     * 删除ASG记录
     * @param id
     * @return
     */
    public int delete(Integer id) { return logMapper.delete(id); }
}
