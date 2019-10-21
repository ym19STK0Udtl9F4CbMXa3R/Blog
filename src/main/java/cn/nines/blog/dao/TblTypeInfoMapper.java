package cn.nines.blog.dao;

import cn.nines.blog.pojo.TblTypeInfo;
import cn.nines.blog.pojo.TblTypeInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblTypeInfoMapper {
    long countByExample(TblTypeInfoExample example);

    int deleteByExample(TblTypeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblTypeInfo record);

    int insertSelective(TblTypeInfo record);

    List<TblTypeInfo> selectByExample(TblTypeInfoExample example);

    TblTypeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblTypeInfo record, @Param("example") TblTypeInfoExample example);

    int updateByExample(@Param("record") TblTypeInfo record, @Param("example") TblTypeInfoExample example);

    int updateByPrimaryKeySelective(TblTypeInfo record);

    int updateByPrimaryKey(TblTypeInfo record);
}