package cn.nines.blog.dao;

import cn.nines.blog.pojo.SysView;
import cn.nines.blog.pojo.SysViewExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysViewMapper {
    long countByExample(SysViewExample example);

    int deleteByExample(SysViewExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysView record);

    int insertSelective(SysView record);

    List<SysView> selectByExample(SysViewExample example);

    SysView selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysView record, @Param("example") SysViewExample example);

    int updateByExample(@Param("record") SysView record, @Param("example") SysViewExample example);

    int updateByPrimaryKeySelective(SysView record);

    int updateByPrimaryKey(SysView record);
}