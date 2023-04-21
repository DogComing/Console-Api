package com.platform.api;

import com.platform.entity.ConfigDogVo;
import com.platform.service.ApiConfigDogService;
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


@Api(tags = "狗狗配置表相关控制层")
@RestController
@RequestMapping("/admin-api/dog")
public class ApiConfigDogController extends ApiBaseAction {

    @Autowired
    private ApiConfigDogService dogService;

    /**
     * 获取狗狗列表
     * @param name 狗狗名称
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
            params.put("dogName", name);

            Query query = new Query(params);
            int total = dogService.queryTotal(query);
            List<ConfigDogVo> dogVoList = dogService.queryList(params);
            ApiPageUtils goodsData = new ApiPageUtils(dogVoList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(goodsData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 获取狗狗详情
     * @param id 装备Id
     * @return
     */
    @ApiOperation(value = " 获取狗狗详情")
    @GetMapping(value = "detail/{id}")
    public Object detail(@PathVariable("id") Integer id) {

        try {

            ConfigDogVo dogVo = dogService.queryObject(id);
            if (null == dogVo) return toResponsObject(400, "该装备不存在", null);
            return toResponsSuccess(dogVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 新增狗狗
     * @param dogVo
     * @return
     */
    @ApiOperation(value = "新增狗狗")
    @PostMapping("add")
    public Object addGoods(@RequestBody ConfigDogVo dogVo) {

        try {
            dogVo.setCreateTime(new Date());
            dogService.save(dogVo);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("添加失败");
    }

    /**
     * 修改狗狗信息
     * @param id
     * @param dogVo
     * @return
     */
    @ApiOperation(value = "修改狗狗信息")
    @PutMapping("update/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody ConfigDogVo dogVo) {

        try {

            ConfigDogVo configDogVo = dogService.queryObject(id);
            if (null == configDogVo) return toResponsObject(400, "该狗狗不存在", null);

            Map map = new HashMap();
            map.put("id", id);
            map.put("dogName", dogVo.getDogName());
            map.put("dogDesc", dogVo.getDogDesc());
            map.put("dogBreed", dogVo.getDogBreed());
            map.put("dogGrade", dogVo.getDogGrade());
            map.put("imgName", dogVo.getImgName());
            map.put("animationName", dogVo.getAnimationName());
            map.put("initialFightingNum", dogVo.getInitialFightingNum());
            map.put("growUpNum", dogVo.getGrowUpNum());
            map.put("inbornNum", dogVo.getInbornNum());
            map.put("updateTime", new Date());
            dogService.update(map);

            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }

    /**
     * 删除狗狗
     * @param id
     * @return
     */
    @ApiOperation(value = "删除狗狗")
    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable(value = "id") Integer id) {

        try {
            ConfigDogVo configDogVo = dogService.queryObject(id);
            if (null == configDogVo) return toResponsObject(400, "该狗狗不存在", null);

            dogService.delete(id);
            return toResponsSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsSuccess(false);
    }
}
