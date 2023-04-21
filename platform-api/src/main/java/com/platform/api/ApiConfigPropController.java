package com.platform.api;

import com.platform.entity.ConfigPropVo;
import com.platform.service.ApiConfigPropService;
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


@Api(tags = "道具配置表相关控制层")
@RestController
@RequestMapping("/admin-api/prop")
public class ApiConfigPropController extends ApiBaseAction {

    @Autowired
    private ApiConfigPropService propService;

    /**
     * 获取道具列表
     * @param name 道具名称
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @ApiOperation(value = "获取道具列表")
    @GetMapping(value = "all")
    public Object group(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            //查询列表数据
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "asc");
            params.put("propName", name);

            Query query = new Query(params);
            int total = propService.queryTotal(query);
            List<ConfigPropVo> propVoList = propService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(propVoList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取道具详情
     * @param id 道具id
     * @return
     */
    @ApiOperation(value = " 获取道具详情")
    @GetMapping(value = "detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {
            ConfigPropVo propVo = propService.queryObject(id);
            if (null == propVo) return toResponsObject(400, "该道具不存在", null);
            return toResponsSuccess(propVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增道具
     * @param propVo
     * @return
     */
    @ApiOperation(value = "新增道具")
    @PostMapping("add")
    public Object add(@RequestBody ConfigPropVo propVo) {

        try {
            propVo.setCreateTime(new Date());
            propService.save(propVo);
            return toResponsSuccess(propVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改道具信息
     * @param id
     * @param propVo
     * @return
     */
    @ApiOperation(value = "修改道具信息")
    @PutMapping("update/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody ConfigPropVo propVo) {

        try {

            ConfigPropVo configPropVo = propService.queryObject(id);
            if (null == configPropVo) return toResponsObject(400, "该道具不存在", null);

            Map map = new HashMap();
            map.put("id", id);
            map.put("grade", propVo.getGrade());
            map.put("propName", propVo.getPropName());
            map.put("propDesc", propVo.getPropDesc());
            map.put("imgName", propVo.getImgName());
            map.put("propType", propVo.getPropType());
            map.put("useType", propVo.getUseType());
            map.put("attributeType", propVo.getAttributeType());
            map.put("updateTime", new Date());
            propService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }

    /**
     * 删除道具
     * @param id
     * @return
     */
    @ApiOperation(value = "删除道具")
    @DeleteMapping("delete/{id}")
    public Object deleteGoods(@PathVariable(value = "id") Integer id) {

        try {
            ConfigPropVo propVo = propService.queryObject(id);
            if (null == propVo) return toResponsObject(400, "该道具不存在", null);

            propService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
