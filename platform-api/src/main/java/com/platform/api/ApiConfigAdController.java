package com.platform.api;

import com.platform.entity.ConfigAdVo;
import com.platform.service.ApiConfigAdService;
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
@RequestMapping("/admin-api/ad")
public class ApiConfigAdController extends ApiBaseAction {

    @Autowired
    private ApiConfigAdService adService;

    /**
     * 获取广告列表
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @ApiOperation(value = "获取广告列表")
    @GetMapping("all")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");

            //查询列表数据
            Query query = new Query(params);
            int total = adService.queryTotal(query);
            List<ConfigAdVo> adVoList = adService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(adVoList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取广告详情
     * @param id 广告Id
     * @return
     */
    @ApiOperation(value = "获取广告详情")
    @GetMapping("detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {
            ConfigAdVo adVo = adService.queryObject(id); // 查询装备
            if (null == adVo) return toResponsObject(400, "该广告不存在", null);
            return toResponsSuccess(adVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增广告
     * @param adVo
     * @return
     */
    @ApiOperation(value = "新增广告")
    @PostMapping("add")
    public Object add(@RequestBody ConfigAdVo adVo) {

        try {
            adVo.setCreateTime(new Date());
            adService.save(adVo);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改广告信息
     * @param id
     * @param params
     * @return
     */
    @ApiOperation(value = "修改广告信息")
    @PutMapping("update/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody Map params){

        try {
            ConfigAdVo adVo = adService.queryObject(id);
            if (null == adVo) return toResponsObject(400, "该广告不存在", null);
            params.put("id", id);
            adService.update(params);
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

            ConfigAdVo configAdVo = adService.queryObject(id);
            if (null == configAdVo) return toResponsObject(400, "该广告不存在", null);

            adService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
