package com.setup.entity;

public class Page {
    // 当前页码，默认1
    private int current = 1;
    // 每页显示，本题默认2
    private int limit = 2;
    // 总页数
    private int rows;
    //数据总数
    private int count;
    // 查询路径(用于复用分页链接)
    private String path;
    //当前主题
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
