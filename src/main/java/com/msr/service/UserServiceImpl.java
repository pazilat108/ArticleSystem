package com.msr.service;

import com.msr.mapper.UserMapper;
import com.msr.pojo.User;
import com.msr.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author acer
 * @create 2025-01-22 16:18
 * @desc
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    //根据用户名来查找用户对象
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        //对密码进行MD5加密
        String md5password =Md5Util.getMD5String(password);
        userMapper.register(username,md5password);
    }
}
