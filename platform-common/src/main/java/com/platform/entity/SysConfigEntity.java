package com.platform.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置信息
 *
 * @author xingGuangTeam
 * @email 249893127@qq.com
 * @date 2016年12月4日 下午6:43:36
 */
@Data
public class SysConfigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 键【属性名字】
     */
    private String key;

    /**
     * 值【属性值】
     */
    private String value;

    /**
     * 属性状态
     */
    private Integer status;

    /**
     * 属性描述
     */
    private String remark;
}
