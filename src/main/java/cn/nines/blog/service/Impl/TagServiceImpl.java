package cn.nines.blog.service.Impl;

import cn.nines.blog.dao.TblArticleTagMapper;
import cn.nines.blog.dao.TblTagInfoMapper;
import cn.nines.blog.pojo.TblArticleTag;
import cn.nines.blog.pojo.TblArticleTagExample;
import cn.nines.blog.pojo.TblTagInfo;
import cn.nines.blog.pojo.TblTagInfoExample;
import cn.nines.blog.service.TagService;
import cn.nines.blog.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Resource
    private TblTagInfoMapper tagMapper;

    @Resource
    private TblArticleTagMapper articleTagMapper;

    @Override
    public TblTagInfo findByName(String name) {
        TblTagInfoExample example = new TblTagInfoExample();
        TblTagInfoExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<TblTagInfo> tags = tagMapper.selectByExample(example);
        if (tags.size() > 0){
            return tags.get(0);
        }
        return null;
    }

    @Override
    public PageResult findPage(TblTagInfo tag, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TblTagInfoExample example = new TblTagInfoExample();
        TblTagInfoExample.Criteria criteria = example.createCriteria();

        if (tag != null){
            if (tag.getName() != null && tag.getName().length() > 0){
                criteria.andNameLike("%" + tag.getName() + "%");
            }
            if (tag.getIsEffective() != null && tag.getIsEffective()){
                criteria.andIsEffectiveEqualTo(true);
            }
        }

        Page<TblTagInfo> page = (Page<TblTagInfo>) tagMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int addTag(TblTagInfo tag) {
        tag.setIsEffective(true);
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        return tagMapper.insert(tag);
    }

    @Override
    public void deleteTag(Long id) {
        TblTagInfo type = tagMapper.selectByPrimaryKey(id);
        type.setIsEffective(false);
        type.setUpdateTime(new Date());
        tagMapper.updateByPrimaryKey(type);
    }

    @Override
    public void recoverTag(Long id) {
        TblTagInfo type = tagMapper.selectByPrimaryKey(id);
        type.setUpdateTime(new Date());
        type.setIsEffective(true);
        tagMapper.updateByPrimaryKey(type);
    }

    @Override
    public int updateTag(TblTagInfo tag) {
        TblTagInfo oldType = tagMapper.selectByPrimaryKey(tag.getId());
        if (oldType == null){
            return 0;
        }
        tag.setUpdateTime(new Date());
        return tagMapper.updateByPrimaryKey(tag);
    }

    @Override
    public TblTagInfo findOne(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TblTagInfo> findValid() {
        TblTagInfoExample example = new TblTagInfoExample();
        TblTagInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIsEffectiveEqualTo(true);
        return tagMapper.selectByExample(example);
    }

    @Override
    public List<TblTagInfo> findTop() {
        PageHelper.startPage(1, 10);
        Page<TblTagInfo> page = (Page<TblTagInfo>) tagMapper.selectByExample(null);
        return page.getResult();
    }

    @Override
    public List<Map> findByArticleIds(List<Long> articleIds) {

        List<Map> result = new ArrayList<>();

        for (Long articleId : articleIds) {
            TblArticleTagExample example = new TblArticleTagExample();
            example.createCriteria().andArticleIdEqualTo(articleId);
            List<TblArticleTag> articleTagList = articleTagMapper.selectByExample(example);

            List<TblTagInfo> tagInfoList = new ArrayList<>();

            for (TblArticleTag tblArticleTag : articleTagList) {
                TblTagInfo tagInfo = tagMapper.selectByPrimaryKey(tblArticleTag.getTagId());
                tagInfoList.add(tagInfo);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("articleId", articleId);
            map.put("tagInfoList", tagInfoList);

            result.add(map);
        }
        return result;
    }
}
