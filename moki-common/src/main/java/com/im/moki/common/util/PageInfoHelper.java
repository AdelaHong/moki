package com.im.moki.common.util;

import com.github.pagehelper.Page;

import java.util.List;

public class PageInfoHelper {
    public static <T> PageInfo pageToPageInfo(Page<T> page, List<T> list) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(list);
        pageInfo.setPagesize(page.getPageSize());
        pageInfo.setNowpage(page.getPageNum());
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }
}
