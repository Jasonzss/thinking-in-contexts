package com.jason.tics.comment.domain;

/**
 * @author Jason
 */
public class ApprovableComment extends Comment {
    private Integer approveNum;

    public ApprovableComment(Long commentId) {
        this(commentId, 0);
    }

    public ApprovableComment(Long commentId, Integer approveNum) {
        super(commentId);
        this.approveNum = approveNum;
    }

    public void approve(long... uid){

    }

    public void disapprove(long... uid){

    }
}
