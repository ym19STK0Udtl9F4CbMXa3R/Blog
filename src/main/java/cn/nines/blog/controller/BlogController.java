package cn.nines.blog.controller;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.pojo.TblArticleInfo;
import cn.nines.blog.pojogroup.Articles;
import cn.nines.blog.service.BlogService;
import cn.nines.blog.util.JsonResult;
import cn.nines.blog.util.PageResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/admin")
@RestController
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private BlogService blogService;

    @ApiOperation(value = "获取分类信息", notes = "根据条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "分类", required = false, dataType = "TblTypeInfo", paramType = "body"),
            @ApiImplicitParam(name = "num", value = "当前页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = false, dataType = "int", paramType = "path")
    })
    @PostMapping("/blogs/search")
    public PageResult search(@RequestBody TblArticleInfo article, int num, int size){
        System.out.println(article.toString());
        return blogService.findPage(article, num, size);
    }

    @ApiOperation(value = "添加博文", notes = "添加博文信息")
    @ApiImplicitParam(name = "articles", value = "博文", required = true, dataType = "Articles", paramType = "body")
    @PostMapping("/blogs")
    public JsonResult addArticle(@RequestBody Articles articles){
        try {
            blogService.addArticle(articles);
            return new JsonResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult(ExceptionEnum.INSERT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "发布博文", notes = "发布博文信息")
    @ApiImplicitParam(name = "articles", value = "博文", required = true, dataType = "Articles", paramType = "body")
    @PostMapping("/blogs/publish")
    public JsonResult publishArticle(@RequestBody Articles articles){
        try {
            if (articles.getArticleInfo().getId() == null){
                // 未保存
                articles.getArticleInfo().setIsPublished(true);
                blogService.addArticle(articles);
            }else {
                blogService.publishArticle(articles.getArticleInfo());
            }
            return new JsonResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult(ExceptionEnum.PUBLISH_FAILED);
        }
    }

    @ApiOperation(value = "删除博文", notes = "删除博文信息")
    @ApiImplicitParam(name = "id", value = "博文id", required = true, defaultValue = "long", paramType = "path")
    @DeleteMapping("/blogs/{id}")
    public JsonResult deleteArticle(@PathVariable Long id){
        try {
            blogService.deleteArticle(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.DELECT_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "恢复博文", notes = "恢复博文信息")
    @ApiImplicitParam(name = "id", value = "博文id", required = true, defaultValue = "long", paramType = "path")
    @PutMapping("/blogs/{id}")
    public JsonResult recoverArticle(@PathVariable Long id){
        try {
            blogService.recoverArticle(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.RECOVER_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "修改博文", notes = "更新博文信息")
    @ApiImplicitParam(name = "articles", value = "博文", required = true, defaultValue = "Articles", paramType = "body")
    @PutMapping("/blogs")
    public JsonResult updateArticle(@RequestBody Articles articles){
        try {
            blogService.updateArticle(articles);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(ExceptionEnum.UPDATE_EXECUTION_FAILED);
        }
    }

    @ApiOperation(value = "获取博文", notes = "根据id获取博文信息")
    @ApiImplicitParam(name = "id", value = "博文id", required = true, defaultValue = "long", paramType = "path")
    @GetMapping("/blogs/{id}")
    public Articles findOne(@PathVariable Long id){
        try {
            return blogService.findOne(id);
        }catch (Exception e){
            return null;
        }
    }

}
