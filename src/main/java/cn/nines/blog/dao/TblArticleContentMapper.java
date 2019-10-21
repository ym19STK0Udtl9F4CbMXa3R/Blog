package cn.nines.blog.dao;

import cn.nines.blog.pojo.TblArticleContent;
import cn.nines.blog.pojo.TblArticleContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblArticleContentMapper {
    long countByExample(TblArticleContentExample example);

    int deleteByExample(TblArticleContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblArticleContent record);

    int insertSelective(TblArticleContent record);

    List<TblArticleContent> selectByExampleWithBLOBs(TblArticleContentExample example);

    List<TblArticleContent> selectByExample(TblArticleContentExample example);

    TblArticleContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblArticleContent record, @Param("example") TblArticleContentExample example);

    int updateByExampleWithBLOBs(@Param("record") TblArticleContent record, @Param("example") TblArticleContentExample example);

    int updateByExample(@Param("record") TblArticleContent record, @Param("example") TblArticleContentExample example);

    int updateByPrimaryKeySelective(TblArticleContent record);

    int updateByPrimaryKeyWithBLOBs(TblArticleContent record);

    int updateByPrimaryKey(TblArticleContent record);
}