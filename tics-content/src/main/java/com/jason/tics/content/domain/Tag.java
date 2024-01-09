package com.jason.tics.content.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Jason
 */
@Getter
@Setter
@ToString
@TableName("tag")
public class Tag {
    private String tagName;
    private String id;

    public Tag() {

    }

    public Tag(String tagName, String id) {
        this.tagName = tagName;
        this.id = id;
    }
}
