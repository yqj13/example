package com.imli.controller;

import com.imli.controller.Request.LoginRequest;
import com.imli.pojo.Admin;
import com.imli.pojo.Result;
import com.imli.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest){
//        loginService.login(loginRequest);
        return loginService.login(loginRequest) ? Result.success() : Result.error("账号或密码错误");
    }
    @PostMapping("/logon")
    public Result logon(@RequestBody Admin ad){
//        loginService.login(loginRequest);
        loginService.logon(ad);
        return Result.success();
    }

}
