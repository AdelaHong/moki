package com.im.moki.common.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageInfo<T> implements Serializable {
    /**默认显示的记录数 */
    private final static int PAGESIZE = 10;

    /**总记录*/
    private Long total;
    /**显示的记录*/
    private List<T> rows;
    /**显示的尾部*/
    private List footer;

    private int from;

    private int totalPage;//总页数

    private int size;

    /**当前页*/
    private int nowpage;

    /**每页显示的记录数*/
    private int pagesize;

    /**查询条件*/
    private Map<String, Object> condition;

    /**排序字段*/
    private String sort = "seq";

    /**asc，desc mybatis Order 关键字*/
    private String order = "asc";

    public PageInfo() {}

    //构造方法
    public PageInfo(int nowpage, int pagesize) {
        //计算当前页
        if (nowpage < 0) {
            this.nowpage = 1;
        } else {
            //当前页
            this.nowpage = nowpage;
        }
        //记录每页显示的记录数
        if (pagesize < 0) {
            this.pagesize = PAGESIZE;
        } else {
            this.pagesize = pagesize;
        }
        //计算开始的记录和结束的记录
        this.from = (this.nowpage - 1) * this.pagesize;
        this.size = this.pagesize;
    }

    // 构造方法
    public PageInfo(int nowpage, int pagesize, String sort, String order) {
        // 计算当前页
        if (nowpage < 0) {
            this.nowpage = 1;
        } else {
            // 当前页
            this.nowpage = nowpage;
        }
        // 记录每页显示的记录数
        if (pagesize < 0) {
            this.pagesize = PAGESIZE;
        } else {
            this.pagesize = pagesize;
        }
        // 计算开始的记录和结束的记录
        this.from = (this.nowpage - 1) * this.pagesize;
        this.size = this.pagesize;
        // 排序字段，正序还是反序
        this.sort = sort;
        this.order = order;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
        calculate();
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List getFooter() {
        return footer;
    }

    public void setFooter(List footer) {
        this.footer = footer;
    }
    /**
     * 自动计算总页数
     */
    private void calculate() {
        //计算总页数
        totalPage = (int) (total % pagesize == 0 ? total / pagesize : total / pagesize + 1);
        if (totalPage != 0) {
            //计算当前页
            nowpage = totalPage < nowpage ? totalPage : nowpage;
        }
    }
}
