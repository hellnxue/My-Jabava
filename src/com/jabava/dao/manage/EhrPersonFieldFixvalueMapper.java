package com.jabava.dao.manage;

import com.jabava.pojo.manage.EhrPersonFieldFixvalue;

public interface EhrPersonFieldFixvalueMapper {
    int deleteByPrimaryKey(Long personFieldFixvalueId);

    int insert(EhrPersonFieldFixvalue record);

    int insertSelective(EhrPersonFieldFixvalue record);

    EhrPersonFieldFixvalue selectByPrimaryKey(Long personFieldFixvalueId);

    int updateByPrimaryKeySelective(EhrPersonFieldFixvalue record);

    int updateByPrimaryKey(EhrPersonFieldFixvalue record);
}