package org.og.fmall.commonapi.utils;

import lombok.Data;

import java.util.List;

/**
 * @author: og
 * @description:
 * @date: 2019/11/5
 */
@Data
public class Page<T> {
    private int pages;
    private int pageNum;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int nextPage;
    private int prePage;
    private List<T> list;
}
