package com.platform.api;

import com.platform.annotation.LoginUser;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysUserService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "管理员接口")
@RestController
@RequestMapping("/admin-api")
public class SysUserController extends ApiBaseAction {

    @Autowired
    private SysUserService userService;

    /***
     * @Description: 获取系统管理员列表
     * @Param: [loginUser, page：第几页, size：每页几条数据]
     * @return: java.lang.Object
     * @Author: Yuan
     * @Date: 2020/9/10
     */
//    @ApiOperation(value = "获取系统管理员列表")
//    @RequestMapping("list")
//    public Object getSysUserList(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
//
//        try {
//            Map params = new HashMap();
//            params.put("page", page);
//            params.put("limit", size);
//            params.put("sidx", "admin_id");
//            params.put("order", "asc");
//
//            //查询列表数据
//            Query query = new Query(params);
//            int total = userService.queryTotal(query);
//            List<SysUserEntity> sysUserEntities = userService.queryList(query);
//            ApiPageUtils pageUtil = new ApiPageUtils(sysUserEntities, total, query.getLimit(), query.getPage());
//            return toResponsSuccess(pageUtil);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return toResponsFail("网络异常，请重试");
//    }

    @ApiOperation(value = "获取管理员信息")
    @GetMapping("admin/info")
    public Object getSysUserList(@LoginUser SysUserEntity loginUser) {

        try {
            SysUserEntity userEntity = userService.queryByAdminId(loginUser.getAdminId().toString());
            return toResponsSuccess(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /***
     * @Description: 新增系统管理员
     * @Param: [loginUser]
     * @return: java.lang.Object
     * @Author: Yuan
     * @Date: 2020/8/6
     */
    @ApiOperation(value = "新增系统管理员")
    @PostMapping("add")
    public Object addSysUser(@RequestBody SysUserEntity userEntity) {

        try {
            Date nowTime = new Date();
            userEntity.setCreateTime(nowTime);
            userEntity.setPassword(new Sha256Hash(userEntity.getPassword()).toHex());
            userService.save(userEntity); //新增系统管理员用户信息
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /***
     * @Description: 更新系统管理员信息
     * @Param: [loginUser]
     * @return: java.lang.Object
     * @Author: Yuan
     * @Date: 2020/8/6
     */
    @ApiOperation(value = "更新系统管理员信息")
    @PutMapping("update")
    public Object updateSysUser(@LoginUser SysUserEntity loginUser, @RequestBody SysUserEntity userEntity) {

        try {
            Map map = new HashMap();
            if (loginUser.getAdminId() == 1) map.put("accountNumber", userEntity.getAccountNumber());
            map.put("adminName", userEntity.getAdminName());
            map.put("mobile", userEntity.getMobile());
            map.put("adminId", userEntity.getAdminId());
            userService.update(map); //新增系统管理员用户信息

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /***
     * @Description: 修改管理者状态
     * @Param: [loginUser]
     * @return: java.lang.Object
     * @Author: Yuan
     * @Date: 2020/8/6
     */
    @ApiOperation(value = "修改管理者状态")
    @PutMapping("update/status")
    public Object updateUserStatus(@RequestBody SysUserEntity userEntity){

        try {
            Map map = new HashMap();
            map.put("status", userEntity.getStatus());
            map.put("adminId", userEntity.getAdminId());

            //更新管理者状态
            userService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /***
     * @Description: 修改密码
     * @Param: [loginUser]
     * @return: java.lang.Object
     * @Author: Yuan
     * @Date: 2020/8/6
     */
    @ApiOperation(value = "修改密码")
    @PutMapping("update/password")
    public Object updatePassword(@LoginUser SysUserEntity loginUser, @RequestParam(value = "accountNumber") String accountNumber, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword){

        try {

            if (loginUser.getAdminId() == 1){
                Map map = new HashMap();
                map.put("password", new Sha256Hash(newPassword).toHex());
                userService.update(map);

                return toResponsSuccess(true);
            }
            //sha256加密
            oldPassword = new Sha256Hash(oldPassword).toHex();
            //sha256加密
            newPassword = new Sha256Hash(newPassword).toHex();

            Map map = new HashMap();
            map.put("accountNumber", accountNumber);
            map.put("oldPassword", oldPassword);
            map.put("newPassword", newPassword);

            //更新密码
            int count = userService.updatePassword(map);
            if (count == 0) {
                return R.error("原密码不正确");
            }

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

}
