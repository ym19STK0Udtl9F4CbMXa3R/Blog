package cn.nines.blog.service.Impl;

import cn.nines.blog.dao.TblCommentMapper;
import cn.nines.blog.pojo.TblComment;
import cn.nines.blog.pojo.TblCommentExample;
import cn.nines.blog.pojogroup.Comment;
import cn.nines.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private TblCommentMapper commentMapper;

    @Value("${CHAT_HEAD}")
    private static String chat_head;

    @Override
    public void addComment(TblComment comment, String ip) {

        comment.setIp(ip);
        comment.setIsEffective(true);
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());

        if (comment.getChatHead() == null){
            comment.setChatHead(chat_head);
        }

        if (comment.getParentId() == null){
            comment.setParentId((long) 0);
        }

        if (comment.getParentNikeName() == null){
            comment.setParentNikeName("");
        }

        if (comment.getIsBlogger() == null){
            comment.setIsBlogger(false);
        }

        commentMapper.insert(comment);

    }

    @Override
    public List<Comment> findCommentByArticleId(Long id) {
        TblCommentExample example = new TblCommentExample();
        example.setOrderByClause("create_time ASC");
        example.createCriteria().andIsEffectiveEqualTo(true).andArticleIdEqualTo(id);
        // 获取该文章的所有评论
        List<TblComment> commentList = commentMapper.selectByExample(example);

        List<Comment> comments = new ArrayList<>();

        for (TblComment tblComment : commentList) {
            // 循环找到顶级评论
            if (tblComment.getParentId() == 0){
                List<TblComment> tblCommentList = new ArrayList<>();
                // 递归生产子评论
                eachComment(tblComment.getId(), commentList, tblCommentList);
                comments.add(new Comment(tblComment, tblCommentList));
            }
        }

        return comments;
    }

    private void eachComment(Long id, List<TblComment> commentList, List<TblComment> tblCommentList){
        for (TblComment comment : commentList) {
            if (comment.getParentId().longValue() == id.longValue()){
                tblCommentList.add(comment);
                eachComment(comment.getId(), commentList, tblCommentList);
            }
        }
    }

}
