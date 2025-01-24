package com.msr.mapper;

import com.msr.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //根据用户名来查找用户对象
    @Select("select * from user where username=#{username}")
    public User findByUsername(String username);

    //用户注册
    @Insert("insert into user (username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void register(String username, String password);

    //修改用户
    @Update("update user set nickname=#{nickname}, email=#{email},update_time=now() where id=#{id}")
    void update(User user);

    //修改头像
    @Update("update user set user_pic=#{userPic},update_time=now() where id=#{id}")
    void updateAvatar(String userPic, Integer id);
    //修改密码
    @Update("update user set password=#{newPwd},update_time=now() where id=#{id}")
    void updatePwd(String newPwd,Integer id);
}
