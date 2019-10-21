package cn.nines.blog.pojogroup;

import cn.nines.blog.pojo.TblComment;

import java.util.List;

public class Comment {

    private TblComment comment;

    private List<TblComment> commentList;

    public Comment(TblComment comment, List<TblComment> commentList) {
        this.comment = comment;
        this.commentList = commentList;
    }

    public TblComment getComment() {
        return comment;
    }

    public void setComment(TblComment comment) {
        this.comment = comment;
    }

    public List<TblComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<TblComment> commentList) {
        this.commentList = commentList;
    }
}
