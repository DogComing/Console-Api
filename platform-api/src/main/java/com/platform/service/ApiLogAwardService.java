package com.platform.service;

import com.platform.dao.ApiLogAwardMapper;
import com.platform.entity.LogAwardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiLogAwardService {

    @Autowired
    private ApiLogAwardMapper logAwardMapper;

    /**
     * 查询某条押注记录详情
     * @param id
     * @return
     */
    public LogAwardVo queryObject(Integer id) { return logAwardMapper.queryObject(id); }

    /**
     * 根据条件查询押注记录列表
     * @param map
     * @return
     */
    public List<LogAwardVo> queryList(Map<String, Object> map){ return logAwardMapper.queryList(map); }

    /**
     * 获取押注总数
     * @param map
     * @return
     */
    public int queryTotal(Map<String, Object> map) { return logAwardMapper.queryTotal(map); }

    /**
     * 删除押注记录
     * @param id
     * @return
     */
    public int delete(Integer id) { return logAwardMapper.delete(id); }

}
