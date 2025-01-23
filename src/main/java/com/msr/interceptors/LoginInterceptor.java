package com.msr.interceptors;

import com.msr.utils.JwtUtil;
import com.msr.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author acer
 * @create 2025-01-23 12:18
 * @desc
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //通过请求头获取令牌
       String token = request.getHeader("Authorization");
       //验证令牌
        try {
            //如果代码执行到此处，则说明验证是成功的
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //保存登录令牌到threadlocalutil
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(401); //权限不足
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //释放资源
        ThreadLocalUtil.remove();
    }
}
