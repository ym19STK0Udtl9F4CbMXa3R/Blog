package cn.nines.blog.controller;

import cn.nines.blog.exception.ExceptionEnum;
import cn.nines.blog.pojo.TblArticleInfo;
import cn.nines.blog.pojo.TblComment;
import cn.nines.blog.pojo.TblUser;
import cn.nines.blog.pojogroup.Articles;
import cn.nines.blog.pojogroup.Comment;
import cn.nines.blog.service.BlogService;
import cn.nines.blog.service.CommentService;
import cn.nines.blog.util.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class commentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    @PostMapping("/save")
    public JsonResult save(HttpServletRequest request, @RequestBody TblComment comment){
        // 判断是否登录
        TblUser user = (TblUser) SecurityUtils.getSubject().getPrincipal();
        if (user != null){
            // 将登录信息替换评论信息
            comment.setNikeName(user.getNikeName());
            comment.setChatHead(user.getChatHead());
            // 获取博客信息
            TblArticleInfo articleInfo = blogService.findOne(comment.getArticleId()).getArticleInfo();
            // 判断登陆者是否是博主
            if (articleInfo.getAuthorId().longValue() == user.getId().longValue()){
                comment.setIsBlogger(true);
            }
        }

        String ip = request.getRemoteAddr();
        try {
            commentService.addComment(comment, ip);
            return new JsonResult();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new JsonResult(ExceptionEnum.INSERT_EXECUTION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public List<Comment> find(@PathVariable Long id){
        try {
            return commentService.findCommentByArticleId(id);
        }catch (Exception e){
            return null;
        }
    }

}
