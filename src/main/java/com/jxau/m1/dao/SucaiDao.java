package com.jxau.m1.dao;

import com.jxau.m1.model.Sucai;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SucaiDao {
    @Insert("insert into sucai(mediaid,type,userid) value(#{mediaid},#{type},#{userid})")
    public int insert(Sucai sucai);
    @Select("select * from sucai where id = #{id}")
    public Sucai selectById(int id);
}
