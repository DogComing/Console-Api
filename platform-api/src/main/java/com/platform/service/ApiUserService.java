package com.platform.service;

import com.platform.dao.ApiUserMapper;
import com.platform.entity.UserVo;
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
}
