package cn.nines.blog.dao;

import cn.nines.blog.pojo.TblTagInfo;
import cn.nines.blog.pojo.TblTagInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblTagInfoMapper {
    long countByExample(TblTagInfoExample example);

    int deleteByExample(TblTagInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblTagInfo record);

    int insertSelective(TblTagInfo record);

    List<TblTagInfo> selectByExample(TblTagInfoExample example);

    TblTagInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblTagInfo record, @Param("example") TblTagInfoExample example);

    int updateByExample(@Param("record") TblTagInfo record, @Param("example") TblTagInfoExample example);

    int updateByPrimaryKeySelective(TblTagInfo record);

    int updateByPrimaryKey(TblTagInfo record);
}