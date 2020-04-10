package com.jxau.m1.dao;

import com.jxau.m1.model.EventMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EventMapDao {
    @Insert("insert into eventMap(eventtype,mediaid,type,userid) value(#{eventType},#{mediaid},#{type},#{userid})")
    public void insert(EventMap eventMap);

    @Select("select * from eventMap where userid = #{userid}")
    public List<EventMap> selectByUserid(int userid);

}
