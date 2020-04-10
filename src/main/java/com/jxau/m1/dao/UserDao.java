package com.jxau.m1.dao;

import com.jxau.m1.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    @Insert("insert into user(username,password) value(#{username},#{password})")
    public int insert(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    public User select( User user);
}
