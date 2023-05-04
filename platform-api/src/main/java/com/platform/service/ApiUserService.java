package com.platform.service;

import com.platform.dao.ApiUserMapper;
import com.platform.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiUserService {

    @Autowired
    private ApiUserMapper userMapper;

    public UserVo queryObject (Long userId) { return userMapper.queryObject(userId); }

    public List<UserVo> queryList (Map<String, Object> map) { return userMapper.queryList(map); }

    public int queryTotal (Map<String, Object> map) {
        return userMapper.queryTotal(map);
    }

    public void update (Map<String, Object> map) { userMapper.update(map); }

    public UserVo queryObjectByName (String userName) { return userMapper.queryObjectByName(userName); }

    public List<UserVo> queryUserList (List<LogAwardVo> logList) { return userMapper.queryUserList(logList); }

    public List<UserVo> queryUserByPay (List<LogPayVo> logList) { return userMapper.queryUserByPay(logList); }

    public List<UserVo> queryUserByAsg (List<LogASGVo> logList) { return userMapper.queryUserByAsg(logList); }

    public List<UserVo> queryUserByBack (List<LogRakeBackVo> logList) { return userMapper.queryUserByBack(logList); }

    public List<UserVo> queryUserByCash (List<LogGameResultVo> logList) { return userMapper.queryUserByCash(logList); }
}
