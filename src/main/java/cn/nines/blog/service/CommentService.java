package cn.nines.blog.service;

import cn.nines.blog.pojo.TblComment;
import cn.nines.blog.pojogroup.Comment;

import java.util.List;

public interface CommentService {

    public void addComment(TblComment comment, String ip);

    public List<Comment> findCommentByArticleId(Long id);

}
