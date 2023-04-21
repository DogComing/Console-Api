package com.platform.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * 登录对象
 */
@Data
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户
     */
    private String accountNumber;
    /**
     * 密码
     */
    private String password;
}
