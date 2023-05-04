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
    @Autowired
    private ApiUserService userService;

    /**
     * 用户押注记录
     * @param gameNum 局号
     * @param page 第几页
     * @param size 每页个数
     * @return
     */
    @ApiOperation(value = "用户押注记录")
    @GetMapping("award")
    public Object scoreLogList(@RequestParam(value = "gameNum", defaultValue = "") String gameNum, @RequestParam(value = "address", defaultValue = "") String address, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("gameNum", gameNum);
            params.put("address", address);

            //查询列表数据
            Query query = new Query(params);
            int total = logAwardService.queryTotal(query);
            List<LogAwardVo> logList = logAwardService.queryList(query);
            if (logList.size() <= 0) {
                ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());
                return toResponsSuccess(pageUtil);
            }
            List<UserVo> userVoList = userService.queryUserList(logList);
            for (int i = 0; i < userVoList.size(); i++){
                for (int j = 0; j < logList.size(); j++){
                    if (logList.get(j).getUserId().equals(userVoList.get(i).getUserId())){
                        logList.get(j).setUserName(userVoList.get(i).getUserName());
                    }
                }
            }
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
    public Object moneyLogList(@RequestParam(value="userName", defaultValue = "") String userName, @RequestParam(value = "address", defaultValue = "") String address, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {

        try {

            UserVo userVo = null;
            if (!userName.isEmpty()) userVo = userService.queryObjectByName(userName);

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("address", address);
            if (userVo != null) params.put("userId", userVo.getUserId());

            //查询列表数据
            Query query = new Query(params);
            int total = logPayService.queryTotal(query);
            List<LogPayVo> logList = logPayService.queryList(query);
            if (logList.size() <= 0) {
                ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());
                return toResponsSuccess(pageUtil);
            }
            List<UserVo> userVoList = userService.queryUserByPay(logList);
            for (int i = 0; i < userVoList.size(); i++){
                for (int j = 0; j < logList.size(); j++){
                    if (logList.get(j).getUserId().equals(userVoList.get(i).getUserId())){
                        logList.get(j).setUserName(userVoList.get(i).getUserName());
                    }
                }
            }
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
    public Object depositLogList(@RequestParam(value="userName", defaultValue = "") String userName, @RequestParam(value = "address", defaultValue = "") String address, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10")Integer size){

        try {

            UserVo userVo = null;
            if (!userName.isEmpty()) userVo = userService.queryObjectByName(userName);

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("address", address);
            if (userVo != null) params.put("userId", userVo.getUserId());

            //查询列表数据
            Query query = new Query(params);
            int total = logASGService.queryTotal(query);
            List<LogASGVo> logList = logASGService.queryList(query);
            if (logList.size() <= 0) {
                ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());
                return toResponsSuccess(pageUtil);
            }
            List<UserVo> userVoList = userService.queryUserByAsg(logList);
            for (int i = 0; i < userVoList.size(); i++){
                for (int j = 0; j < logList.size(); j++){
                    if (logList.get(j).getUserId().equals(userVoList.get(i).getUserId())){
                        logList.get(j).setUserName(userVoList.get(i).getUserName());
                    }
                }
            }
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
    public Object confirmCashOut(@RequestParam(value="userName", defaultValue = "") String userName, @RequestParam(value = "address", defaultValue = "") String address, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10")Integer size){

        try {
            UserVo userVo = null;
            if (!userName.isEmpty()) userVo = userService.queryObjectByName(userName);

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("address", address);
            if (userVo != null) params.put("userId", userVo.getUserId());

            //查询列表数据
            Query query = new Query(params);
            int total = logRakeBackService.queryTotal(query);
            List<LogRakeBackVo> logList = logRakeBackService.queryList(query);
            if (logList.size() <= 0) {
                ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());
                return toResponsSuccess(pageUtil);
            }
            List<UserVo> userVoList = userService.queryUserByBack(logList);
            for (int i = 0; i < userVoList.size(); i++){
                for (int j = 0; j < logList.size(); j++){
                    if (logList.get(j).getUserId().equals(userVoList.get(i).getUserId())){
                        logList.get(j).setUserName(userVoList.get(i).getUserName());
                    }
                }
            }
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
    public Object cancelCashOut(@RequestParam(value="gameNum", defaultValue = "") String gameNum, @RequestParam(value="userName", defaultValue = "") String userName, @RequestParam(value = "address", defaultValue = "") String address, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10")Integer size){

        try {
            UserVo userVo = null;
            if (!userName.isEmpty()) userVo = userService.queryObjectByName(userName);

            Map params = new HashMap();
            params.put("page", page);
            params.put("limit", size);
            params.put("sidx", "id");
            params.put("order", "desc");
            params.put("gameNum", gameNum);
            if (userVo != null) params.put("userId", userVo.getUserId());

            //查询列表数据
            Query query = new Query(params);
            int total = logGameResultService.queryTotal(query);
            List<LogGameResultVo> logList = logGameResultService.queryList(query);
            if (logList.size() <= 0) {
                ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());
                return toResponsSuccess(pageUtil);
            }
            List<UserVo> userVoList = userService.queryUserByCash(logList);
            for (int i = 0; i < userVoList.size(); i++){
                for (int j = 0; j < logList.size(); j++){
                    if (logList.get(j).getUserId().equals(userVoList.get(i).getUserId())){
                        logList.get(j).setUserName(userVoList.get(i).getUserName());
                    }
                }
            }
            ApiPageUtils pageUtil = new ApiPageUtils(logList, total, query.getLimit(), query.getPage());

            return toResponsSuccess(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toResponsFail("网络异常，请重试");
    }

}
