package com.msr.mapper;

import com.msr.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //根据用户名来查找用户对象
    @Select("select * from user where username=#{username}")
    public User findByUsername(String username);

    //用户注册
    @Insert("insert into user (username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void register(String username, String password);
}
