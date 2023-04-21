package com.platform.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: platform
 * @description: 定时任务
 * @author: Yuan
 * @create: 2020-09-12 15:28
 **/
@Component
@Scope("singleton")
public class ScheduledTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * @Description: 每隔20分钟从数据库获取一下最新的accessToken
     * @Param: []
     * @return: void
     * @Author: Yuan
     * @Date: 2020/6/22
     */
    @Scheduled(cron = "0 */20 * * * ?")
    private void refreshToken(){

    }

    /***
     * @Description: 每天凌晨一点自动释放积分
     * @Param: []
     * @return: void
     * @Author: Yuan
     * @Date: 2020/6/1
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void releaseIntegral() {

    }

}
