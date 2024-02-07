package com.jason.tics.dictionary.service.impl;

import cn.hutool.db.PageResult;
import com.jason.tics.comment.core.Comment;
import com.jason.tics.comment.core.CommonCommentArea;
import com.jason.tics.common.core.exception.ExceptionResponseEnum;
import com.jason.tics.common.core.exception.TicsException;
import com.jason.tics.dictionary.domain.dto.CommentDto;
import com.jason.tics.dictionary.service.CommentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jason
 */

@Service
@Getter
@Setter
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommonCommentArea commonCommentArea;

    @Override
    public PageResult<Comment> listSortedComments(String id, String sort, boolean descend, int page, int size) {
        if (sort.equals(Comment.SORT_COLUMN_CREATE_TIME)) {
            if (!descend){
                return commonCommentArea.listCommentEarliestPage(id, page, size);
            } else {
                return commonCommentArea.listCommentLatestPage(id, page, size);
            }
        } else if (sort.equals(Comment.SORT_COLUMN_LIKE_NUM)){
            return commonCommentArea.listCommentMostLikedPage(id, page, size);
        } else {
            throw new TicsException(ExceptionResponseEnum.BAD_URL_PARAM);
        }
    }

    @Override
    public Comment addComment(CommentDto commentDto) {
        return commonCommentArea.addComment(commentDto.getSubjectId(), commentDto.getUid(), commentDto.getContent());
    }

    @Override
    public void deleteComment(long cid) {
        commonCommentArea.deleteComment(cid);
    }

    @Override
    public void deleteCommentAttachments(long cid, String[] attachment) {
        commonCommentArea.deleteCommentAttachments(cid, attachment);
    }

    @Override
    public String updateCommentContent(long cid, String content) {
        //TODO 这样写不太妥
        return commonCommentArea.modifyCommentContent(cid, content) ? content : "";
    }

    @Override
    public void likeComment(long uid, long cid) {
        commonCommentArea.likeComment(cid, uid);
    }

    @Override
    public void disLikeComment(long uid, long cid) {
        commonCommentArea.dislikeComment(cid, uid);
    }

    @Override
    public String[] addCommentAttachments(long cid, String[] attachments) {
        return commonCommentArea.addCommentAttachments(cid, attachments);
    }

    @Override
    public Comment getComment(long cid) {
        return commonCommentArea.getComment(cid);
    }
}
