package cn.nines.blog.service.Impl;

import cn.nines.blog.dao.TblArticleContentMapper;
import cn.nines.blog.dao.TblArticleInfoMapper;
import cn.nines.blog.dao.TblArticleTagMapper;
import cn.nines.blog.dao.TblTypeInfoMapper;
import cn.nines.blog.pojo.*;
import cn.nines.blog.pojogroup.Articles;
import cn.nines.blog.service.BlogService;
import cn.nines.blog.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Resource
    private TblTypeInfoMapper typeInfoMapper;

    @Resource
    private TblArticleTagMapper articleTagMapper;

    @Resource
    private TblArticleInfoMapper articleInfoMapper;

    @Resource
    private TblArticleContentMapper articleContentMapper;

    // 后台查看
    @Override
    public PageResult findPage(TblArticleInfo article, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TblArticleInfoExample example = new TblArticleInfoExample();
        example.setOrderByClause("create_time DESC");
        TblArticleInfoExample.Criteria criteria = example.createCriteria();

        if (article != null){
            if (article.getTitle() != null && article.getTitle().length() > 0 ){
                criteria.andTitleLike("%" + article.getTitle() + "%");
            }
            if (article.getIsRecommend() != null && article.getIsRecommend()){
                criteria.andIsRecommendEqualTo(true);
            }
            if (article.getIsEffective() != null && article.getIsEffective()){
                criteria.andIsEffectiveEqualTo(true);
            }
            if (article.getTypeId() != null){
                criteria.andTypeIdEqualTo(article.getTypeId());
            }
        }

        Page<TblArticleInfo> page = (Page<TblArticleInfo>) articleInfoMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    // 首页
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TblArticleInfoExample example = new TblArticleInfoExample();
        example.setOrderByClause("create_time DESC");
        example.createCriteria().andIsEffectiveEqualTo(true);

        Page<TblArticleInfo> page = (Page<TblArticleInfo>) articleInfoMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(Long tagId, int pageNum, int pageSize) {
        TblArticleInfoExample example = new TblArticleInfoExample();
        example.setOrderByClause("create_time DESC");
        TblArticleInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIsEffectiveEqualTo(true);

        if (tagId != null && tagId != 0){
            TblArticleTagExample tagExample = new TblArticleTagExample();
            tagExample.createCriteria().andTagIdEqualTo(tagId);
            List<TblArticleTag> articleInfoList = articleTagMapper.selectByExample(tagExample);
            List<Long> articleIdList = new ArrayList<>();
            for (TblArticleTag tblArticleTag : articleInfoList) {
                articleIdList.add(tblArticleTag.getArticleId());
            }
            criteria.andIdIn(articleIdList);
        }

        PageHelper.startPage(pageNum, pageSize);

        Page<TblArticleInfo> page = (Page<TblArticleInfo>) articleInfoMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(String searchData, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TblArticleInfoExample example = new TblArticleInfoExample();
        example.setOrderByClause("create_time DESC");
        TblArticleInfoExample.Criteria criteria = example.createCriteria();

        criteria.andIsEffectiveEqualTo(true);

        if (searchData != null && searchData.length() > 0){
            criteria.andTitleLike("%"+ searchData +"%");
        }

        Page<TblArticleInfo> page = (Page<TblArticleInfo>) articleInfoMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void addArticle(Articles article) {

        TblUser user = (TblUser) SecurityUtils.getSubject().getPrincipal();
        Long authorId = user.getId();
        String authorNikeName = user.getNikeName();
        String authorChatHead = user.getChatHead();

        TblArticleInfo articleInfo = article.getArticleInfo();

        articleInfo.setAuthorId(authorId);
        articleInfo.setAuthorNikeName(authorNikeName);
        articleInfo.setAuthorChatHead(authorChatHead);

        articleInfo.setViews(0);
//        articleInfo.setIsPublished(false);
        articleInfo.setIsEffective(true);
        articleInfo.setCreateTime(new Date());
        articleInfo.setUpdateTime(new Date());
        articleInfoMapper.insert(articleInfo);

        TblArticleContent articleContent = article.getArticleContent();
        articleContent.setArticleId(articleInfo.getId());
        articleContent.setCreateTime(new Date());
        articleContent.setUpdateTime(new Date());
        articleContentMapper.insert(articleContent);

        List<Long> tagList = article.getTagIds();
        for (Long tagId : tagList) {
            TblArticleTag articleTag = new TblArticleTag(articleInfo.getId(), tagId, new Date(), new Date());
            articleTagMapper.insert(articleTag);
        }

        // 更新数量
        addTypeNum(articleInfo.getTypeId());
    }

    public void addTypeNum(Long id){
        TblTypeInfo type = typeInfoMapper.selectByPrimaryKey(id);
        type.setNum((byte) (type.getNum() + 1));
        typeInfoMapper.updateByPrimaryKey(type);
    }

    @Override
    public void publishArticle(TblArticleInfo article) {
        article.setIsPublished(true);
        article.setUpdateTime(new Date());
        articleInfoMapper.updateByPrimaryKey(article);
    }

    @Override
    public void deleteArticle(Long id) {
        TblArticleInfo article = articleInfoMapper.selectByPrimaryKey(id);
        article.setIsEffective(false);
        article.setUpdateTime(new Date());
        articleInfoMapper.updateByPrimaryKey(article);

        // 更新数量
        divTypeNum(article.getTypeId());
    }

    public void divTypeNum(Long id){
        TblTypeInfo type = typeInfoMapper.selectByPrimaryKey(id);
        type.setNum((byte) (type.getNum() - 1));
        typeInfoMapper.updateByPrimaryKey(type);
    }

    @Override
    public void recoverArticle(Long id) {
        TblArticleInfo article = articleInfoMapper.selectByPrimaryKey(id);
        article.setIsEffective(true);
        articleInfoMapper.updateByPrimaryKey(article);

        addTypeNum(article.getTypeId());
    }

    @Override
    public void updateArticle(Articles article) {
        TblArticleInfo articleInfo = article.getArticleInfo();

        // 更新标签数量
        TblArticleInfo oldArticleInfo = articleInfoMapper.selectByPrimaryKey(articleInfo.getId());
        Long oldTypeId = oldArticleInfo.getTypeId();
        if (oldTypeId.longValue() != articleInfo.getTypeId().longValue()){
            changeTypeNum(articleInfo.getTypeId(), oldTypeId);
        }
        // 更新 info 内容
        articleInfo.setUpdateTime(new Date());
        articleInfoMapper.updateByPrimaryKey(articleInfo);

        TblArticleContent articleContent = article.getArticleContent();
        articleContent.setArticleId(articleInfo.getId());
        articleContent.setUpdateTime(new Date());
        articleContentMapper.updateByPrimaryKeyWithBLOBs(articleContent);

        List<Long> tagIds = article.getTagIds();
        TblArticleTagExample tagExample = new TblArticleTagExample();
        TblArticleTagExample.Criteria tagCriteria = tagExample.createCriteria();
        tagCriteria.andArticleIdEqualTo(articleInfo.getId());
        List<TblArticleTag> tagList = articleTagMapper.selectByExample(tagExample);

        // 循环原来的标签列表和现在的标签列表对比
        // 更新后还存在则从两个列表中移除
        // 最后删除原来列表中剩余的标签
        // 添加新列表中的标签
        Iterator<TblArticleTag> tagListIter = tagList.iterator();
        Iterator<Long> tagIdsIter = tagIds.iterator();
        while (tagListIter.hasNext()){
            TblArticleTag tagInfo = tagListIter.next();
            while (tagIdsIter.hasNext()){
                Long tagId = tagIdsIter.next();
                if (tagInfo.getTagId().longValue() == tagId.longValue()){
                    tagListIter.remove();
                    tagIdsIter.remove();
                }
            }
        }

        // 删除更新后原来的无用标签
        if (tagList.size() > 0){
            for (TblArticleTag tblArticleTag : tagList) {
//                System.out.println(tblArticleTag.getTagId());
                articleTagMapper.deleteByPrimaryKey(tblArticleTag.getId());
            }
        }

        // 添加更新后原来没有的标签
        if (tagIds.size() > 0){
            for (Long tagId : tagIds) {
//                System.out.println(tagId);
                articleTagMapper.insert(new TblArticleTag(articleInfo.getId(), tagId, new Date(), new Date()));
            }
        }
    }

    public void changeTypeNum(Long newId, Long oldId){
        addTypeNum(newId);
        divTypeNum(oldId);
    }

    @Override
    public Articles findOne(Long id) {
        TblArticleInfo article = articleInfoMapper.selectByPrimaryKey(id);

        TblArticleContent content = null;

        TblArticleContentExample example = new TblArticleContentExample();
        TblArticleContentExample.Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(id);
        List<TblArticleContent> contentList = articleContentMapper.selectByExampleWithBLOBs(example);
        if (contentList != null && contentList.size() > 0){
             content = contentList.get(0);
        }

        TblArticleTagExample tagExample = new TblArticleTagExample();
        TblArticleTagExample.Criteria tagCriteria = tagExample.createCriteria();
//        tagExample.setOrderByClause("tag_id ASC");
        tagCriteria.andArticleIdEqualTo(id);
        List<TblArticleTag> tagList = articleTagMapper.selectByExample(tagExample);
        List<Long> tagIdList = new ArrayList<>();
        for (TblArticleTag tag : tagList) {
            tagIdList.add(tag.getTagId());
        }
//        System.out.println(tagIdList.toString());
        return new Articles(article, content, tagIdList);
    }

    @Override
    public List<TblArticleInfo> findTop() {
        PageHelper.startPage(1, 8);

        TblArticleInfoExample example = new TblArticleInfoExample();
        example.setOrderByClause("update_time DESC");
        Page<TblArticleInfo> page = (Page<TblArticleInfo>) articleInfoMapper.selectByExample(example);
        return page.getResult();
    }

    @Override
    public void updateViews(Long id) {
        TblArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(id);
        articleInfo.setViews(articleInfo.getViews() + 1);
        articleInfoMapper.updateByPrimaryKey(articleInfo);
    }

    @Override
    public List<String> getYears() {
        return articleInfoMapper.selectYears();
    }

    @Override
    public List findArchiveBlogByYears(List<String> years) {
        List<Map> list = new ArrayList<>();
        for (String year : years) {
            Map<String, Object> map = new HashMap<>();
            List<TblArticleInfo> articleInfoList = articleInfoMapper.findArchiveBlogByYears(year);
            map.put("year", year);
            map.put("data", articleInfoList);
            list.add(map);
        }
        return list;
    }
}
