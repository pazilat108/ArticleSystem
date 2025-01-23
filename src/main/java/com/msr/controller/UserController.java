package com.msr.controller;

import com.msr.pojo.User;
import com.msr.pojo.entity.Result;
import com.msr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

/**
 * @author acer
 * @create 2025-01-22 16:12
 * @desc
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    //注册功能
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,10}$") String username, @Pattern(regexp = "^\\S{3,10}$") String password){
        //打印参数
        System.out.println("Controller - Username: " + username);
        System.out.println("Controller - Password: " + password);
        //用户是否存在
        User user =userService.findByUsername(username);
        if(user!=null){ //存在
            return  Result.error("用户已被占用");
        }
        //注册
        userService.register(username,password);
        return Result.success();
    }
}
