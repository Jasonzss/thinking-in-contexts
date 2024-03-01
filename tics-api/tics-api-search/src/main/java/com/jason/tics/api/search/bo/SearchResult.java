package com.jason.tics.api.search.bo;

import lombok.Data;

import java.util.List;

/**
 * @author Jason
 */
@Data
public class SearchResult<T> {
    /**
     * 当前页码
     */
    private Integer page;
    /**
     * 总数
     */
    private Long total;
    /**
     * 当前页面数据
     */
    private List<T> data;

    public SearchResult(int page, Long total, List<T> data) {
        this.page = page;
        this.total = total;
        this.data = data;
    }
}
