package com.platform.api;

import com.platform.entity.ConfigWildVo;
import com.platform.service.ApiConfigWildService;
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


@Api(tags = "野生配置表相关控制层")
@RestController
@RequestMapping("/admin-api/wild")
public class ApiConfigWildController extends ApiBaseAction {

    @Autowired
    private ApiConfigWildService wildService;

    /**
     * 获取野生列表
     * @param name 野生名称
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @GetMapping(value = "all")
    @ApiOperation(value = "获取野生列表")
    public Object group(@RequestParam(value = "name", defaultValue = "")String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            //查询列表数据
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "asc");
            params.put("wildName", name);

            Query query = new Query(params);
            int total = wildService.queryTotal(query);
            List<ConfigWildVo> groupList = wildService.queryList(params);
            ApiPageUtils goodsData = new ApiPageUtils(groupList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(goodsData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取野生详情
     * @param id 野生Id
     * @return
     */
    @ApiOperation(value = " 获取野生详情")
    @GetMapping(value = "detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {
            ConfigWildVo wildVo = wildService.queryObject(id);
            if (null == wildVo) return toResponsObject(400, "该野生不存在", null);
            return toResponsSuccess(wildVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增套餐
     */
    @PostMapping("add")
    @ApiOperation(value = "新增套餐")
    public Object add(@RequestBody ConfigWildVo wildVo) {

        try {
            wildVo.setCreateTime(new Date());
            wildService.save(wildVo);
            return toResponsSuccess(wildVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改套餐
     */
    @PutMapping("update/{id}")
    @ApiOperation(value = "修改套餐")
    public Object update(@PathVariable("id") Integer id, @RequestBody ConfigWildVo wildVo) {

        try {

            ConfigWildVo configWildVo = wildService.queryObject(id);
            if (null == configWildVo) return toResponsObject(400, "该野生不存在", null);

            Map map = new HashMap();
            map.put("id", id);
            map.put("wildName", wildVo.getWildName());
            map.put("wildDesc", wildVo.getWildDesc());
            map.put("grade", wildVo.getGrade());
            map.put("imgName", wildVo.getImgName());
            map.put("fightingNum", wildVo.getFightingNum());
            map.put("durability", wildVo.getDurability());
            map.put("isFight", wildVo.getIsFight());
            map.put("isIgnoreTalent", wildVo.getIsIgnoreTalent());
            map.put("wildType", wildVo.getWildType());
            map.put("updateTime", new Date());
            wildService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }

    /**
     * 删除野生
     * @param id
     * @return
     */
    @ApiOperation(value = "删除野生")
    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable(value = "id") Integer id) {

        try {
            ConfigWildVo wildVo = wildService.queryObject(id);
            if (null == wildVo) return toResponsObject(400, "该野生不存在", null);

            wildService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
