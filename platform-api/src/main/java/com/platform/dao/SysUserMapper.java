package com.platform.dao;

import com.platform.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @program: platform
 * @description: 系统用户相关
 * @author: Yuan
 * @create: 2020-09-12 15:47
 **/
@Mapper
public interface SysUserMapper extends BaseDao<SysUserEntity> {

    /**
     * 根据管理用户id查询用户
     * @param adminId
     * @return
     */
    SysUserEntity queryByAdminId(@Param("adminId") String adminId);

    /**
     * 根据账号查询用户
     * @param accountNumber
     * @return
     */
    SysUserEntity queryByAccountNumber(@Param("accountNumber") String accountNumber);

    /**
     * 修改密码
     * @param map
     * @return
     */
    int updatePassword(Map<String, Object> map);
}

