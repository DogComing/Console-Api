package com.platform.entity;

import com.platform.validator.group.AddGroup;
import com.platform.validator.group.UpdateGroup;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: platform
 * @description: 系统用户实体类
 * @author: Yuan
 * @create: 2020-09-09 10:20
 **/
@Data
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long adminId;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminName;
    /**
     * 账号
     */
    private transient String accountNumber;
    /**
     * 密码
     */
    private transient String password;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态文字  0：禁用   1：正常
     */
    private String statusTxt;
    /**
     * 是否超级管理员
     */
    private Boolean isChildren;

    public String getStatusTxt() {

        if (null != status && StringUtils.isEmpty(statusTxt)) {

            statusTxt = "";
            switch (status) {
                case 0:
                    statusTxt = "禁用";
                    break;
                case 1:
                    statusTxt = "正常";
                    break;
            }
        }
        return statusTxt;
    }
}
