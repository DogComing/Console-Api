package com.platform.api;

import com.platform.entity.*;
import com.platform.service.*;
import com.platform.util.ApiBaseAction;
import com.platform.util.ApiPageUtils;
import com.platform.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "记录相关控制层")
@RestController
@RequestMapping("/admin-api/log")
public class ApiLogController extends ApiBaseAction {

    @Autowired
    private ApiLogAwardService logAwardService;
    @Autowired
    private ApiLogPayService logPayService;
    @Autowired
    private ApiLogASGService logASGService;
    @Autowired
    private ApiLogRakeBackService logRakeBackService;
    @Autowired
    private ApiLogGameResultService logGameResultService;
    @Autowired
    private ApiLogAdService logAdService;

    /**
     * 用户押注记录
     * @param gameNum 局号
     * @param page 第几页
     * @param size 每页个数
     * @return
     */
    @ApiOperation(value = "用户押注记录")
    @GetMapping("award")
    public Object scoreLogList(@RequestParam("gameNum") String gameNum, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("gameNum", gameNum);

            //查询列表数据
            Query query = new Query(params);
            int total = logAwardService.queryTotal(query);
            List<LogAwardVo> logList = logAwardService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());
            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 用户支付记录
     * @param userName 用户昵称
     * @param page 第几页
     * @param size 每页个数
     * @return
     */
    @ApiOperation(value = "用户支付记录")
    @GetMapping("pay")
    public Object moneyLogList(@RequestParam("userName") String userName, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("userName", userName);

            //查询列表数据
            Query query = new Query(params);
            int total = logPayService.queryTotal(query);
            List<LogPayVo> logList = logPayService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 用户ASG记录
     * @param userName 用户昵称
     * @param page 第几页
     * @param size 每页个数
     * @return
     */
    @ApiOperation(value = "用户ASG记录")
    @GetMapping("asg")
    public Object depositLogList(@RequestParam("userName") String userName, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10")Integer size){

        try {
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("userName", userName);

            //查询列表数据
            Query query = new Query(params);
            int total = logASGService.queryTotal(query);
            List<LogASGVo> logList = logASGService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 用户返佣记录
     * @param userName 用户昵称
     * @param page 第几页
     * @param size 每页个数
     * @return
     */
    @ApiOperation(value = "用户返佣记录")
    @GetMapping("rake")
    public Object confirmCashOut(@RequestParam("userName") String userName, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10")Integer size){

        try {
            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("userName", userName);

            //查询列表数据
            Query query = new Query(params);
            int total = logRakeBackService.queryTotal(query);
            List<LogRakeBackVo> logList = logRakeBackService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

    /**
     * 用户赞助记录
     * @param gameNum 局号
     * @param userName 用户昵称
     * @param page 第几页
     * @param size 每页个数
     * @return
     */
    @ApiOperation(value = "用户赞助记录")
    @GetMapping("support")
    public Object cancelCashOut(@RequestParam("gameNum") String gameNum, @RequestParam("userName") String userName, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10")Integer size){

        try {

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("userName", gameNum);

            //查询列表数据
            Query query = new Query(params);
            int total = logGameResultService.queryTotal(query);
            List<LogGameResultVo> logList = logGameResultService.queryList(query);
            ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

}
