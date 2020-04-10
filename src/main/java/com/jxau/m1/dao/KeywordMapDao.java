package com.jxau.m1.dao;

import com.jxau.m1.model.KeywordMap;
import com.jxau.m1.model.Sucai;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KeywordMapDao {
    @Insert("insert into keywordMap(keyword,type,mediaid,userid) value(#{keyword},#{type},#{mediaid},#{userid})")
    public int insert(KeywordMap keywordMap);

    @Select("select * from keywordMap where userid = #{userid}")
    public List<KeywordMap> selectByUserid(int userid);
}
