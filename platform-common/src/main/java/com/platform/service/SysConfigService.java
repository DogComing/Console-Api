package com.platform.service;


import com.platform.entity.SysConfigEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 *
 * @author xingGuangTeam
 * @email 249893127@qq.com
 * @date 2019年12月4日 下午6:49:01
 */
public interface SysConfigService {

    /**
     * 根据key 查询 value
     * @param key
     * @return
     */
    String queryByKey(String key);

    /**
     * 保存配置信息
     * @param config
     */
    void save(SysConfigEntity config);

    /**
     * 更新配置信息
     * @param config
     */
    void update(SysConfigEntity config);

    /**
     * 根据key，更新value
     * @param key
     * @param value
     */
    void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     * @param ids
     */
    void deleteBatch(Long[] ids);

    /**
     * 获取List列表
     * @param map
     * @return
     */
    List<SysConfigEntity> queryList(Map<String, Object> map);

    /**
     * 获取根据key获取平台配置
     * @param key
     * @return
     */
    SysConfigEntity queryObject(String key);

    /**
     * 根据key，获取配置的value值
     * @param key
     * @param defaultValue
     * @return
     */
    String getValue(String key, String defaultValue);

    /**
     * 根据key，获取value的Object对象
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getConfigObject(String key, Class<T> clazz);

}
