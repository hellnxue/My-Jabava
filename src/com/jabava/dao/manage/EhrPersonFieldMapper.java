package com.jabava.dao.manage;

import com.jabava.pojo.manage.EhrPersonField;

public interface EhrPersonFieldMapper {
    int deleteByPrimaryKey(Integer fieldId);

    int insert(EhrPersonField record);

    int insertSelective(EhrPersonField record);

    EhrPersonField selectByPrimaryKey(Integer fieldId);

    int updateByPrimaryKeySelective(EhrPersonField record);

    int updateByPrimaryKey(EhrPersonField record);
}