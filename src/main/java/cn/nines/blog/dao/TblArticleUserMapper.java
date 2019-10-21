package cn.nines.blog.dao;

import cn.nines.blog.pojo.TblArticleUser;
import cn.nines.blog.pojo.TblArticleUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblArticleUserMapper {
    long countByExample(TblArticleUserExample example);

    int deleteByExample(TblArticleUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblArticleUser record);

    int insertSelective(TblArticleUser record);

    List<TblArticleUser> selectByExample(TblArticleUserExample example);

    TblArticleUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblArticleUser record, @Param("example") TblArticleUserExample example);

    int updateByExample(@Param("record") TblArticleUser record, @Param("example") TblArticleUserExample example);

    int updateByPrimaryKeySelective(TblArticleUser record);

    int updateByPrimaryKey(TblArticleUser record);
}