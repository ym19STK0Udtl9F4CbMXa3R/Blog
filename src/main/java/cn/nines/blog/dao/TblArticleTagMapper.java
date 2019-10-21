package cn.nines.blog.dao;

import cn.nines.blog.pojo.TblArticleTag;
import cn.nines.blog.pojo.TblArticleTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblArticleTagMapper {
    long countByExample(TblArticleTagExample example);

    int deleteByExample(TblArticleTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblArticleTag record);

    int insertSelective(TblArticleTag record);

    List<TblArticleTag> selectByExample(TblArticleTagExample example);

    TblArticleTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblArticleTag record, @Param("example") TblArticleTagExample example);

    int updateByExample(@Param("record") TblArticleTag record, @Param("example") TblArticleTagExample example);

    int updateByPrimaryKeySelective(TblArticleTag record);

    int updateByPrimaryKey(TblArticleTag record);
}