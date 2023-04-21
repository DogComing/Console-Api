package com.platform.api;

import com.platform.entity.ConfigVo;
import com.platform.service.ApiConfigService;
import com.platform.util.ApiBaseAction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "系统配置信息")
@RestController
@RequestMapping("/admin-api/setting")
public class SysConfigController extends ApiBaseAction {

    @Autowired
    private ApiConfigService configService;

    /**
     * 获取所有配置列表
     * @return
     */
    @ApiOperation(value = "获取所有配置列表")
    @GetMapping("/all")
    public Object list() {

        try {
            List<ConfigVo> configList = configService.queryService();
            return toResponsSuccess(configList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess("网络异常，请重试");
    }

    /**
     * 根据键值key获取配置信息
     * @param key
     * @return
     */
    @ApiOperation(value = "根据键值key获取配置信息")
    @GetMapping("/info/{key}")
    public Object info(@PathVariable("key") String key) {

        try {
            ConfigVo config = configService.queryObject(key);
            return toResponsSuccess(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess("网络异常，请重试");
    }

    /**
     * 修改配置
     * @param config
     * @return
     */
    @ApiOperation(value = "修改配置")
    @PutMapping("/update")
    public Object update(@RequestBody ConfigVo config) {

        try {

            Map map = new HashMap();
            map.put("id", config.getId());
            map.put("key", config.getKey());
            map.put("value", config.getValue());
            map.put("status", config.getStatus());
            map.put("remark", config.getRemark());
            map.put("updateTime", new Date());
            configService.updateValueByKey(map);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("操作异常");
    }

}
