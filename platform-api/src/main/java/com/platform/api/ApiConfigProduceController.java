package com.platform.api;

import com.platform.annotation.IgnoreAuth;
import com.platform.entity.*;
import com.platform.service.*;
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


@Api(tags = "地图产出配置")
@RestController
@RequestMapping("/admin-api/produce")
public class ApiConfigProduceController extends ApiBaseAction {

    @Autowired
    private ApiConfigMapCatchRatioService mapCatchRatioService;

    @Autowired
    private ApiConfigMapFightRatioService mapFightRatioService;

    @Autowired
    private ApiConfigMapForageRatioService mapForageRatioService;

    @Autowired
    private ApiConfigMapGemRatioService mapGemRatioService;

    @Autowired
    private ApiConfigMapPetRatioService mapPetRatioService;

    @Autowired
    private ApiConfigMapWildRatioService mapWildRatioService;

    /**
     * 获取地图产出概率
     * @param type 类型 1：狗狗 2：捕捉装备 3：对战装备 4：饲料 5：珍宝 6：野生
     * @param page 第几页
     * @param size 每页几条数据
     * @return
     */
    @IgnoreAuth
    @GetMapping(value = "all")
    @ApiOperation(value = "获取地图产出概率")
    public Object group(@RequestParam(value = "type", defaultValue = "0") Integer type, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            //查询列表数据
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "asc");

            int total = 0;
            ApiPageUtils pageUtils = null;
            Query query = new Query(params);

            switch (type){
                case 1:
                    total = mapPetRatioService.queryTotal(query);
                    List<ConfigMapPetRatioVo> petRatioVos = mapPetRatioService.queryList(params);
                    pageUtils = new ApiPageUtils(petRatioVos, total, query.getLimit(), query.getPage());
                    break;
                case 2:
                    total = mapCatchRatioService.queryTotal(query);
                    List<ConfigMapCatchRatioVo> catchRatioVos = mapCatchRatioService.queryList(params);
                    pageUtils = new ApiPageUtils(catchRatioVos, total, query.getLimit(), query.getPage());
                    break;
                case 3:
                    total = mapFightRatioService.queryTotal(query);
                    List<ConfigMapFightRatioVo> fightRatioVos = mapFightRatioService.queryList(params);
                    pageUtils = new ApiPageUtils(fightRatioVos, total, query.getLimit(), query.getPage());
                    break;
                case 4:
                    total = mapForageRatioService.queryTotal(query);
                    List<ConfigMapForageRatioVo> forageRatioVos = mapForageRatioService.queryList(params);
                    pageUtils = new ApiPageUtils(forageRatioVos, total, query.getLimit(), query.getPage());
                    break;
                case 5:
                    total = mapGemRatioService.queryTotal(query);
                    List<ConfigMapGemRatioVo> gemRatioVos = mapGemRatioService.queryList(params);
                    pageUtils = new ApiPageUtils(gemRatioVos, total, query.getLimit(), query.getPage());
                    break;
                case 6:
                    total = mapWildRatioService.queryTotal(query);
                    List<ConfigMapWildRatioVo> wildRatioVos = mapWildRatioService.queryList(params);
                    pageUtils = new ApiPageUtils(wildRatioVos, total, query.getLimit(), query.getPage());
                    break;
                default:
                    break;
            }

            return toResponsSuccess(pageUtils);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取某类物品里某个物品产出概率
     */
    @IgnoreAuth
    @GetMapping(value = "detail")
    @ApiOperation(value = " 获取某类物品里某个物品产出概率")
    public Object detail(@RequestParam("type") Integer type, @RequestParam(value = "id") Integer id) {

        try {

            Object object = null;
            switch (type){
                case 1:
                    object = mapPetRatioService.queryObject(id);
                    break;
                case 2:
                    object = mapCatchRatioService.queryObject(id);
                    break;
                case 3:
                    object = mapFightRatioService.queryObject(id);
                    break;
                case 4:
                    object = mapForageRatioService.queryObject(id);
                    break;
                case 5:
                    object = mapGemRatioService.queryObject(id);
                    break;
                case 6:
                    object = mapWildRatioService.queryObject(id);
                    break;
                default:
                    break;
            }
            return toResponsSuccess(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 修改地图产出配置
     * @param type
     * @param params
     * @return
     */
    @ApiOperation(value = "修改地图产出配置")
    @PutMapping("update/{type}")
    public Object updateGoods(@PathVariable("type") Integer type, @RequestBody Map params) {

        try {

            params.put("updateTime", new Date());
            switch (type){
                case 1:
                    mapPetRatioService.update(params);
                    break;
                case 2:
                    mapCatchRatioService.update(params);
                    break;
                case 3:
                    mapFightRatioService.update(params);
                    break;
                case 4:
                    mapForageRatioService.update(params);
                    break;
                case 5:
                    mapGemRatioService.update(params);
                    break;
                case 6:
                    mapWildRatioService.update(params);
                    break;
                default:
                    break;
            }

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
