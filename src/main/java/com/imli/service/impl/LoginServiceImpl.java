package com.imli.service.impl;

import com.imli.controller.Request.LoginRequest;
import com.imli.exception.ServiceException;
import com.imli.mapper.AdminMapper;
import com.imli.pojo.Admin;
import com.imli.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public boolean login(LoginRequest res) {
       boolean flag = true;
      Admin admin = adminMapper.selectByUsername(res.getUserName());
      if (admin == null){
          flag = false;
//          throw new ServiceException("账号不存在");
      }
      if (!admin.getPassword().equals(res.getPassword())){
          flag = false;
//          throw new ServiceException("用户名或密码错误");
      }
        return flag;
    }

    @Override
    public void logon(Admin ad) {
         adminMapper.insert(ad);
    }
}
