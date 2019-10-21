package cn.nines.blog.dao;

import cn.nines.blog.pojo.TblComment;
import cn.nines.blog.pojo.TblCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblCommentMapper {
    long countByExample(TblCommentExample example);

    int deleteByExample(TblCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblComment record);

    int insertSelective(TblComment record);

    List<TblComment> selectByExample(TblCommentExample example);

    TblComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TblComment record, @Param("example") TblCommentExample example);

    int updateByExample(@Param("record") TblComment record, @Param("example") TblCommentExample example);

    int updateByPrimaryKeySelective(TblComment record);

    int updateByPrimaryKey(TblComment record);
}