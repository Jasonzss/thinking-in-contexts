package com.jason.tics.content.domain;

/**
 * @author Jason
 * @since 2023/09/13 - 14:58
 */
public interface Post {
    Long getPostId();

    Long getOwnerId();

    Cover getCover();
}
