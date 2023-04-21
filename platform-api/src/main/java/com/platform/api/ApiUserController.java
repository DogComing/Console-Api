package com.platform.api;

import com.platform.entity.UserVo;
import com.platform.service.ApiUserService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理接口
 *
 * @author xingGuangTeam
 * @email 249893127@qq.com
 * @date 2019-03-23 15:31
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin-api/user")
public class ApiUserController extends ApiBaseAction {

    @Autowired
    private ApiUserService userService;

    /**
     * 获取用户列表
     * @param userName 用户昵称
     * @param address 钱包地址
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping("all")
    public Object getUserTeamList(@RequestParam(value = "userName", defaultValue = "") String userName, @RequestParam(value = "address", defaultValue = "") String address, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            Map params = new HashMap();
            params.put("userName", userName);
            params.put("address", address);
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");

            //查询列表数据
            Query query = new Query(params);
            int total = userService.queryTotal(query);
            List<UserVo> userVos = userService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(userVos, total, query.getLimit(), query.getPage());
            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 修改个人信息
     * @param userId 用户userId
     * @param params
     * @return
     */
    @ApiOperation(value = "修改个人信息")
    @PutMapping("update/{userId}")
    public Object updateUserInfo(@PathVariable("userId") Long userId, @RequestBody Map params) {

        try {
            UserVo userVo = userService.queryObject(userId);
            if (null == userVo) return toResponsObject(400, "该用户不存在", null);
            params.put("userId", userId);
            userService.update(params);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }

    /**
     * 获取用户信息
     * @param userId 用户userId
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("info/{userId}")
    public Object getUserInfo(@PathVariable("userId") Long userId){

        try {
            UserVo userVo = userService.queryObject(userId); //查询用户信息
            if (null == userVo) return toResponsObject(400, "用户信息错误", null);
            return toResponsSuccess(userVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 设置用户状态
     * @param userId 用户userId
     * @return
     */
    @ApiOperation(value = "设置用户状态")
    @PutMapping("setting/status/{userId}")
    public Object setUserStatus(@PathVariable("userId") Long userId){

        try {

            Integer isStatus = 1;
            UserVo userVo = userService.queryObject(userId);
            if (null == userVo) return toResponsObject(400, "该用户不存在", null);
            if (userVo.getStatus() == 1) isStatus = 2;
            Map map = new HashMap();
            map.put("status", isStatus);
            map.put("userId", userVo.getUserId());
            userService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("操作失败，请重试");
    }

}
