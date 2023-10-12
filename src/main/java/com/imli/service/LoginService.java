package com.imli.service;

import com.imli.controller.Request.LoginRequest;
import com.imli.pojo.Admin;

public interface LoginService {
    boolean login(LoginRequest res);

    void logon(Admin ad);
}
