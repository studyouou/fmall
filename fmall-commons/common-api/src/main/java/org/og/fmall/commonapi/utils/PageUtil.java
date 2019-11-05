package org.og.fmall.commonapi.utils;

import java.util.List;

/**
 * @author: og
 * @description:
 * @date: 2019/11/5
 */
public class PageUtil {

    public static <T> Page createPage(int count, int current, int size){
        return createPage(count,current,size,null);
    }

    public static <T> Page createPage(int count, int current, int size, List<T> list){
        Page page = new Page();
        page.setList(list);
        page.setFirstPage(current == 1);
        page.setPages(getPages(count,size));
        page.setLastPage(current == page.getPages());
        page.setNextPage(getNextPage(current,page.isLastPage()));
        page.setPrePage(page.isFirstPage()?1:current-1);
        page.setPageNum(current);
        return page;
    }

    private static int getNextPage(int current, boolean lastPage) {
        return lastPage?current : current+1;
    }

    private static int getPages(int count, int size) {
        return count/size + count%size == 0?0 : 1;
    }

}
