package com.platform.api;

import com.platform.entity.ConfigCatchEquipVo;
import com.platform.service.ApiConfigCatchEquipService;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "捕捉装备配置表相关控制层")
@RestController
@RequestMapping("/admin-api/catch/equip")
public class ApiConfigCatchEquipController extends ApiBaseAction {

    @Autowired
    private ApiConfigCatchEquipService catchEquipService;

    /**
     * 获取捕捉装备列表
     * @param name 装备名称
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @ApiOperation(value = "获取捕捉装备列表")
    @GetMapping("all")
    public Object list(@RequestParam(value = "name", defaultValue = "")String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("equipName", name);

            //查询列表数据
            Query query = new Query(params);
            int total = catchEquipService.queryTotal(query);
            List<ConfigCatchEquipVo> catchEquipVos = catchEquipService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(catchEquipVos, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取捕捉装备详情
     * @param id 装备Id
     * @return
     */
    @ApiOperation(value = "获取捕捉装备详情")
    @GetMapping("detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {
            ConfigCatchEquipVo catchEquipVo = catchEquipService.queryObject(id); // 查询装备
            if (null == catchEquipVo) return toResponsObject(400, "该装备不存在", null);
            return toResponsSuccess(catchEquipVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增捕捉装备
     * @param catchEquipVo
     * @return
     */
    @ApiOperation(value = "新增捕捉装备")
    @PostMapping("add")
    public Object add(@RequestBody ConfigCatchEquipVo catchEquipVo) {

        try {
            catchEquipVo.setCreateTime(new Date());
            catchEquipService.save(catchEquipVo);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改捕捉装备信息
     * @param id
     * @param params
     * @return
     */
    @ApiOperation(value = "修改捕捉装备信息")
    @PutMapping("update/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody Map params){

        try {
            ConfigCatchEquipVo catchEquipVo = catchEquipService.queryObject(id);
            if (null == catchEquipVo) return toResponsObject(400, "该装备不存在", null);
            params.put("id", id);
            catchEquipService.update(params);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("操作失败");
    }

    /**
     * 删除捕捉装备
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "删除捕捉装备")
    public Object delete(@PathVariable(value = "id") Integer id) {

        try {

            ConfigCatchEquipVo catchEquipVo = catchEquipService.queryObject(id);
            if (null == catchEquipVo) return toResponsObject(400, "该装备不存在", null);

            catchEquipService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
