package com.jason.tics.comment.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
public class Like {
    private Long uid;
    private Long subjectId;
    private Date createTime;
    //点踩功能等后续版本再加

    public Like() {
    }

    public Like(Long uid, Long subjectId) {
        this.uid = uid;
        this.subjectId = subjectId;
    }
}
