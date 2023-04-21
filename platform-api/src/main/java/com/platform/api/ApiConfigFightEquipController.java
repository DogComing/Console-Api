package com.platform.api;

import com.platform.entity.ConfigFightEquipVo;
import com.platform.service.ApiConfigFightEquipService;
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


@Api(tags = "对战装备配置表相关控制层")
@RestController
@RequestMapping("/admin-api/fight/equip")
public class ApiConfigFightEquipController extends ApiBaseAction {

    @Autowired
    private ApiConfigFightEquipService fightEquipService;

    /**
     * 获取对战装备列表
     * @param name 装备名称
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @ApiOperation(value = "获取对战装备列表")
    @GetMapping(value = "all")
    public Object group(@RequestParam(value = "name", defaultValue = "")String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            //查询列表数据
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "asc");
            params.put("equipName", name);

            Query query = new Query(params);
            int total = fightEquipService.queryTotal(query);
            List<ConfigFightEquipVo> fightEquipVos = fightEquipService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(fightEquipVos, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取对战装备详情
     * @param id 装备Id
     * @return
     */
    @ApiOperation(value = " 获取对战装备详情")
    @GetMapping(value = "detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {
            ConfigFightEquipVo fightEquipVo = fightEquipService.queryObject(id);
            if (null == fightEquipVo) return toResponsObject(400, "该装备不存在", null);
            return toResponsSuccess(fightEquipVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增对战装备
     * @param fightEquipVo
     * @return
     */
    @ApiOperation(value = "新增对战装备")
    @PostMapping("add")
    public Object add(@RequestBody ConfigFightEquipVo fightEquipVo) {

        try {
            fightEquipVo.setCreateTime(new Date());
            fightEquipService.save(fightEquipVo);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改对战装备信息
     * @param id
     * @param fightEquipVo
     * @return
     */
    @ApiOperation(value = "修改对战装备信息")
    @PutMapping("update/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody ConfigFightEquipVo fightEquipVo) {

        try {
            System.out.println(fightEquipVo);
            ConfigFightEquipVo configFightEquipVo = fightEquipService.queryObject(id);
            if (null == configFightEquipVo) return toResponsObject(400, "该装备不存在", null);

            Map map = new HashMap();
            map.put("id", id);
            map.put("fightName", fightEquipVo.getFightName());
            map.put("fightDesc", fightEquipVo.getFightDesc());
            map.put("grade", fightEquipVo.getGrade());
            map.put("imgName", fightEquipVo.getImgName());
            map.put("fightingAddition", fightEquipVo.getFightingAddition());
            map.put("isGem", fightEquipVo.getIsGem());
            map.put("fightType", fightEquipVo.getFightType());
            map.put("durability", fightEquipVo.getDurability());
            map.put("updateTime", new Date());
            fightEquipService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }

    /**
     * 删除对战装备
     * @param id
     * @return
     */
    @ApiOperation(value = "删除对战装备")
    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable(value = "id") Integer id) {

        try {

            ConfigFightEquipVo configFightEquipVo = fightEquipService.queryObject(id);
            if (null == configFightEquipVo) return toResponsObject(400, "该装备不存在", null);

            fightEquipService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
