package com.uwaytech.security.ctrl;

import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.IdResult;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.security.ctrl.dto.UserRoleDto;
import com.uwaytech.security.domain.UserRole;
import com.uwaytech.security.service.LoginService;
import com.uwaytech.security.service.RoleInfoService;
import com.uwaytech.spring_interceptors.UnAccessTokenAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户登录
 * Created by lyf on 2016/6/30.
 */
@RestController
@RequestMapping("/security/v0.1")
public class LoginController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(LoginController.class);
    @Resource
    private LoginService loginService;

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @UnAccessTokenAuth
    public Object login(@RequestParam(value = "account") String account,
                        @RequestParam(value = "password") String password) {

        return loginService.login(account, password);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @UnAccessTokenAuth
    public Object register(@RequestParam(value = "account") String account,
                           @RequestParam(value = "password") String password) {
        loginService.register(account, password);
        return new SuccessResult();
    }
}
