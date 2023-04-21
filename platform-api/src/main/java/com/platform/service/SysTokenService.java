package com.platform.service;

import com.platform.dao.SysTokenMapper;
import com.platform.entity.SysTokenVo;
import com.platform.utils.CharUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: platform
 * @description: 用户端创建 Token 到 sys_token 表中
 * @author: Yuan
 * @create: 2020-09-04 09:49
 **/
@Service
public class SysTokenService {

    @Autowired
    private SysTokenMapper tokenMapper;

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;
    //2小时后过期
    private final static int EXPIRE2 = 3600 * 2;

    public SysTokenVo queryByUserId(Long userId) { return tokenMapper.queryByUserId(userId); }

    public SysTokenVo queryByToken(String token) { return tokenMapper.queryByToken(token); }

    public void save(SysTokenVo token) {
        tokenMapper.save(token);
    }

    public void update(SysTokenVo token) {
        tokenMapper.update(token);
    }

    public Map<String, Object> createToken(long adminId) {

        //生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        SysTokenVo sysTokenVo = queryByUserId(adminId);
        if (sysTokenVo == null) {
            sysTokenVo = new SysTokenVo();
            sysTokenVo.setUserId(adminId);
            sysTokenVo.setToken(token);
            sysTokenVo.setUpdateTime(now);
            sysTokenVo.setExpireTime(expireTime);
            save(sysTokenVo); //保存token
        } else {
            sysTokenVo.setToken(token);
            sysTokenVo.setUpdateTime(now);
            sysTokenVo.setExpireTime(expireTime);
            update(sysTokenVo); //更新token
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }
}
