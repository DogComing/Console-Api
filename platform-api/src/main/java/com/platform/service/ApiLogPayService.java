package com.platform.service;

import com.platform.dao.ApiLogPayMapper;
import com.platform.entity.LogPayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiLogPayService {

    @Autowired
    private ApiLogPayMapper logPayMapper;

    /**
     * 查询某条支付记录详情
     * @param id
     * @return
     */
    public LogPayVo queryObject(Integer id) { return logPayMapper.queryObject(id); }

    /**
     * 根据条件查询对支付记录列表
     * @param map
     * @return
     */
    public List<LogPayVo> queryList(Map<String, Object> map){ return logPayMapper.queryList(map); }

    /**
     * 获取支付记录总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return logPayMapper.queryTotal(map); }

    /**
     * 删除支付记录
     * @param id
     * @return
     */
    public int delete(Integer id) { return logPayMapper.delete(id); }
}
