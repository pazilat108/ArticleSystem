package com.msr.controller;

import com.msr.pojo.User;
import com.msr.pojo.entity.Result;
import com.msr.service.UserService;
import com.msr.utils.JwtUtil;
import com.msr.utils.Md5Util;
import com.msr.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

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
    public Result register(@Pattern(regexp = "^\\S{3,10}$") String username, @Pattern(regexp = "^\\S{3,10}$") String password) {
        //用户是否存在
        User user = userService.findByUsername(username);
        if (user != null) { //存在
            return Result.error("用户已被占用");
        }
        //注册
        userService.register(username, password);
        return Result.success();
    }

    //用户登录
    @PostMapping("/login")
    public Result<String> login(String username, String password) {
        //1.通过用户名查找对象
        User loginUser = userService.findByUsername(username);

        //2.判断对象是否存在
        if (loginUser == null) {
            return Result.error("用户不存在!");
        }

        //3.验证密码是否正确
        if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) { //密码加密
            //登录成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            //根据上述提供的claims生成token
            String token = JwtUtil.genToken(claims);
            return Result.success(token); //返回令牌到前端页面
        }
        //密码不正确，登录失败
        return Result.error("密码不正确!");
    }
     //查看用户详情
     @GetMapping("/userInfo")
     public Result userInfo(){
         //获取ThreadLocalUtil的map对象
         Map<String, Object> claims = (Map<String, Object>) ThreadLocalUtil.get();
         String username = claims.get("username").toString();
         //根据用户名获取用户对象
         User user = userService.findByUsername(username);
         return Result.success(user);
    }

    //修改用户
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }
    //修改头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
}

