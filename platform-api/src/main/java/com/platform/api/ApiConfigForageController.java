package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.ConfigForageVo;
import com.platform.service.ApiConfigForageService;
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


@Api(tags = "饲料配置表相关控制层")
@RestController
@RequestMapping("/admin-api/forage")
public class ApiConfigForageController extends ApiBaseAction {

    @Autowired
    private ApiConfigForageService forageService;

    /**
     * 获取饲料列表
     * @param name 饲料名称
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @GetMapping(value = "all")
    @ApiOperation(value = "获取饲料列表")
    public Object group(@RequestParam(value = "name", defaultValue = "")String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            //查询列表数据
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "asc");
            params.put("status", 1);

            Query query = new Query(params);
            int total = forageService.queryTotal(query);
            List<ConfigForageVo> forageVoList = forageService.queryList(query);
            ApiPageUtils goodsData = new ApiPageUtils(forageVoList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(goodsData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取饲料详情
     */
    @IgnoreAuth
    @ApiOperation(value = " 获取饲料详情")
    @GetMapping(value = "detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {
            ConfigForageVo forageVo = forageService.queryObject(id);
            if (null == forageVo) return toResponsObject(400, "该饲料不存在", null);
            return toResponsSuccess(forageVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增饲料
     * @param forageVo
     * @return
     */
    @PostMapping("add")
    @ApiOperation(value = "新增饲料")
    public Object add(@RequestBody ConfigForageVo forageVo) {

        try {
            forageVo.setCreateTime(new Date());
            forageService.save(forageVo);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改饲料信息
     * @param id
     * @param forageVo
     * @return
     */
    @ApiOperation(value = "修改饲料信息")
    @PutMapping("update/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody ConfigForageVo forageVo) {

        try {

            ConfigForageVo configForageVo = forageService.queryObject(id);
            if (null == configForageVo) return toResponsObject(400, "该饲料不存在", null);

            Map map = new HashMap();
            map.put("id", id);
            map.put("forageName", forageVo.getForageName());
            map.put("forageDesc", forageVo.getForageDesc());
            map.put("grade", forageVo.getGrade());
            map.put("imgName", forageVo.getImgName());
            map.put("fightingNum", forageVo.getFightingNum());
            map.put("forageType", forageVo.getForageType());
            map.put("isIgnoreTalent", forageVo.getIsIgnoreTalent());
            map.put("updateTime", new Date());
            forageService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }

    /**
     * 删除饲料
     * @param id
     * @return
     */
    @ApiOperation(value = "删除饲料")
    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable(value = "id") Integer id) {

        try {

            ConfigForageVo configForageVo = forageService.queryObject(id);
            if (null == configForageVo) return toResponsObject(400, "该饲料不存在", null);

            forageService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
