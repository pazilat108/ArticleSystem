package com.msr.service;

import com.msr.pojo.User;

public interface UserService {
    //根据用户名来查找用户对象
    User findByUsername(String username);
    //用户注册
    void register(String username, String password);
}
