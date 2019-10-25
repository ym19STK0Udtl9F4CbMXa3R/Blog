package cn.nines.blog.controller;

import cn.nines.blog.pojo.TblArticleInfo;
import cn.nines.blog.pojo.TblTagInfo;
import cn.nines.blog.pojo.TblTypeInfo;
import cn.nines.blog.pojogroup.Articles;
import cn.nines.blog.service.BlogService;
import cn.nines.blog.service.TagService;
import cn.nines.blog.service.TypeService;
import cn.nines.blog.util.MarkdownUtils;
import cn.nines.blog.util.PageResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {

    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;

    @Resource
    private BlogService blogService;

    @ApiOperation(value = "获取博文信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @GetMapping("/blogPage")
    public PageResult search(int num, int size){
        return blogService.findPage(num, size);
    }

    @ApiOperation(value = "获取博文信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", value = "文章", required = false, dataType = "TblArticleInfo", paramType = "body"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @PostMapping("/blogPage")
    public PageResult search(@RequestBody TblArticleInfo article, int num, int size){
        return blogService.findPage(article, num, size);
    }

    @ApiOperation(value = "获取博文信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", value = "标签ID", required = false, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @PostMapping("/blogPage2")
    public PageResult search(@RequestBody Long tagId, int num, int size){
        return blogService.findPage(tagId, num, size);
    }

    @ApiOperation(value = "获取分类信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchData", value = "搜索内容", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @GetMapping("/search")
    public PageResult search(String searchData, int num, int size){
        return blogService.findPage(searchData, num, size);
    }

    @PostMapping("/findArticleTag")
    public List findArticleTag(@RequestBody List<Long> articleIds){
        System.out.println("articleIds的长度="+ articleIds.size());
        return tagService.findByArticleIds(articleIds);
    }

    @GetMapping("/typeTop")
    public List<TblTypeInfo> findTypeTop(){
        try {
            return typeService.findTop();
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/tagTop")
    public List<TblTagInfo> findTagTop(){
        try {
            return tagService.findTop();
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/blogTop")
    public List<TblArticleInfo> findBlogTop(){
        try {
            return blogService.findTop();
        } catch (Exception e){
            return null;
        }
    }

    @ApiOperation(value = "获取博文", notes = "根据id获取博文信息")
    @ApiImplicitParam(name = "id", value = "博文id", required = true, defaultValue = "long", paramType = "path")
    @GetMapping("/blog/{id}")
    public Articles findBlogParticulars(@PathVariable Long id){
        try {
            // 更新浏览次数
            blogService.updateViews(id);

            Articles articles = blogService.findOne(id);
            articles.getArticleContent().setContent(MarkdownUtils.markdownToHtmlExtensions(articles.getArticleContent().getContent()));
            return articles;
        }catch (Exception e){
            return null;
        }
    }

}
