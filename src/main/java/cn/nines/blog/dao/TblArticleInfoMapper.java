package cn.nines.blog.dao;

import cn.nines.blog.pojo.TblArticleInfo;
import cn.nines.blog.pojo.TblArticleInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblArticleInfoMapper {
    long countByExample(TblArticleInfoExample example);

    int deleteByExample(TblArticleInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblArticleInfo record);

    int insertSelective(TblArticleInfo record);

    List<TblArticleInfo> selectByExample(TblArticleInfoExample example);

    TblArticleInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblArticleInfo record, @Param("example") TblArticleInfoExample example);

    int updateByExample(@Param("record") TblArticleInfo record, @Param("example") TblArticleInfoExample example);

    int updateByPrimaryKeySelective(TblArticleInfo record);

    int updateByPrimaryKey(TblArticleInfo record);

    List<String> selectYears();

    List<TblArticleInfo> findArchiveBlogByYears(String year);
}