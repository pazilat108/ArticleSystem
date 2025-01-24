package com.msr.service;

import com.msr.mapper.UserMapper;
import com.msr.pojo.User;
import com.msr.utils.Md5Util;
import com.msr.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    //修改用户的基本信息
    @Override
    public void update(User user) {
        //获取登录者的用户id
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = Integer.parseInt(claims.get("id").toString());
        user.setId(id);
        userMapper.update(user);
    }
    //修改头像
    @Override
    public void updateAvatar(String avatarUrl) {
        //获取登录者的用户id
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = Integer.parseInt(claims.get("id").toString());
        userMapper.updateAvatar(avatarUrl,id);
    }

    //修改密码
    @Override
    public void updatePwd(String newPwd) {
        //获取登录者的用户id
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = Integer.parseInt(claims.get("id").toString());
        //修改密码，对密码进行MD5加密
        String md5Pwd =Md5Util.getMD5String(newPwd);
        userMapper.updatePwd(md5Pwd,id);
    }
}
