package com.platform.service;

import com.platform.dao.SysUserMapper;
import com.platform.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    public SysUserEntity queryObject(Long adminId) { return userMapper.queryObject(adminId); }

    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return userMapper.queryList(map);
    }

    public int queryTotal(Map<String, Object> map) {
        return userMapper.queryTotal(map);
    }

    public void save(SysUserEntity userEntity) { userMapper.save(userEntity); }

    public void update(Map<String, Object> map) { userMapper.update(map); }

    public SysUserEntity queryByAdminId(String adminId) {return userMapper.queryByAdminId(adminId); }

    public SysUserEntity queryByAccountNumber(String accountNumber) { return userMapper.queryByAccountNumber(accountNumber); }

    public Integer updatePassword(Map<String, Object> map) { return userMapper.updatePassword(map); }
}
