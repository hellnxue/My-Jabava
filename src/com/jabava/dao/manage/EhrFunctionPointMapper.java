package com.jabava.dao.manage;

import com.jabava.pojo.manage.EhrFunctionPoint;

public interface EhrFunctionPointMapper {
    int deleteByPrimaryKey(Integer functionPointCode);

    int insert(EhrFunctionPoint record);

    int insertSelective(EhrFunctionPoint record);

    EhrFunctionPoint selectByPrimaryKey(Integer functionPointCode);

    int updateByPrimaryKeySelective(EhrFunctionPoint record);

    int updateByPrimaryKey(EhrFunctionPoint record);
}