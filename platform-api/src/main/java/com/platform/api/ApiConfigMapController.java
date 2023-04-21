package com.platform.api;

import com.platform.entity.ConfigMapVo;
import com.platform.service.ApiConfigMapService;
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


@Api(tags = "地图配置表相关控制层")
@RestController
@RequestMapping("/admin-api/map")
public class ApiConfigMapController extends ApiBaseAction {

    @Autowired
    private ApiConfigMapService mapService;

    /**
     * 获取地图列表
     * @param name 地图名称
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @ApiOperation(value = "获取狗狗列表")
    @GetMapping(value = "all")
    public Object group(@RequestParam(value = "name", defaultValue = "")String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            //查询列表数据
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "asc");
            params.put("mapName", name);

            Query query = new Query(params);
            int total = mapService.queryTotal(query);
            List<ConfigMapVo> mapVos = mapService.queryList(params);
            ApiPageUtils goodsData = new ApiPageUtils(mapVos, total, query.getLimit(), query.getPage());

            return toResponsSuccess(goodsData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取地图详情
     * @param id 地图Id
     * @return
     */
    @ApiOperation(value = " 获取地图详情")
    @GetMapping(value = "detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {

            ConfigMapVo mapVo = mapService.queryObject(id);
            if (null == mapVo) return toResponsObject(400, "该地图不存在", null);
            return toResponsSuccess(mapVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增地图
     * @param mapVo
     * @return
     */
    @ApiOperation(value = "新增狗狗")
    @PostMapping("add")
    public Object add(@RequestBody ConfigMapVo mapVo) {

        try {
            mapVo.setCreateTime(new Date());
            mapService.save(mapVo);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改地图信息
     * @param id
     * @param mapVo
     * @return
     */
    @ApiOperation(value = "修改地图信息")
    @PutMapping("update/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody ConfigMapVo mapVo) {

        try {

            ConfigMapVo configMapVo = mapService.queryObject(id);
            if (null == configMapVo) return toResponsObject(400, "该地图不存在", null);

            Map map = new HashMap();
            map.put("id", id);
            map.put("mapName", mapVo.getMapName());
            map.put("imgName", mapVo.getImgName());
            map.put("mapGrade", mapVo.getMapGrade());
            map.put("petRatio", mapVo.getPetRatio());
            map.put("equipRatio", mapVo.getEquipRatio());
            map.put("gemRatio", mapVo.getGemRatio());
            map.put("forageRatio", mapVo.getForageRatio());
            map.put("wildRatio", mapVo.getWildRatio());
            map.put("useBrawn", mapVo.getUseBrawn());
            map.put("useAgs", mapVo.getUseAgs());
            map.put("lockLevel", mapVo.getLockLevel());
            map.put("attributeNum", mapVo.getAttributeNum());
            map.put("updateTime", new Date());
            mapService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }

    /**
     * 删除地图
     * @param id
     * @return
     */
    @ApiOperation(value = "删除地图")
    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable(value = "id") Integer id) {

        try {
            ConfigMapVo configMapVo = mapService.queryObject(id);
            if (null == configMapVo) return toResponsObject(400, "该地图不存在", null);

            mapService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
