package com.platform.dao;

import com.platform.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiUserMapper extends BaseDao<UserVo> {


    UserVo queryObjectByName (@Param("userName") String userName);

    List<UserVo> queryUserList (List<LogAwardVo> logAwardList);

    List<UserVo> queryUserByPay (List<LogPayVo> logList);

    List<UserVo> queryUserByAsg (List<LogASGVo> logList);

    List<UserVo> queryUserByBack (List<LogRakeBackVo> logList);

    List<UserVo> queryUserByCash (List<LogGameResultVo> logList);
}
