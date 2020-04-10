package com.jxau.m1.dao;

import com.jxau.m1.model.KeywordMap;
import com.jxau.m1.model.UnknownMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UnknownMapDao {
    @Insert("insert into unknownMap(msgtype,type,mediaid,userid) value(#{msgtype},#{type},#{mediaid},#{userid})")
    public int insert(UnknownMap unknownMap);

    @Select("select * from  unknownMap where userid = #{userid}")
    public List<UnknownMap> selectByUserid(int userid);

}
