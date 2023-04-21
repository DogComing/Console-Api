package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.LoginInfo;
import com.platform.entity.SysUserEntity;
import com.platform.service.SysUserService;
import com.platform.service.SysTokenService;
import com.platform.util.ApiBaseAction;
import com.platform.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/admin-api")
public class SysLoginController extends ApiBaseAction {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysTokenService sysTokenService;

    /**
     * 管理者登录接口
     */
    @IgnoreAuth
    @PostMapping("auth")
    @ApiOperation(value = "账号密码登录")
    public Object userLogin(@RequestBody LoginInfo loginInfo) {

        try {
            // 查询用户信息
            SysUserEntity userEntity = userService.queryByAccountNumber(loginInfo.getAccountNumber());
            if (userEntity == null) return toResponsFail("该账户不存在");
            String password = new Sha256Hash(loginInfo.getPassword()).toHex(); //sha256加密
            if (!password.equals(userEntity.getPassword())) return toResponsFail("账户或密码错误");

            // 更新最后登录时间，登录Ip
            Map map = new HashMap();
            map.put("adminId", userEntity.getAdminId());
            map.put("lastLoginTime", new Date());
            map.put("lastLoginIp", this.getClientIp());
            userService.update(map);

            Map<String, Object> tokenMap = sysTokenService.createToken(userEntity.getAdminId());
            String token = MapUtils.getString(tokenMap, "token");
            if (StringUtils.isNullOrEmpty(token)) return toResponsFail("登录失败");

            return toResponsSuccess(token);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toResponsSuccess("网络异常，请重试");
    }
}
