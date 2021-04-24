package com.setup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


public class PageVue<T> implements Serializable {
    private List<T> list;
    private long total;

    public PageVue() {
    }

    public PageVue(List<T> list) {
        this.list = list;
    }

    public PageVue(long total) {
        this.total = total;
    }

    public PageVue(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageVue{" +
                "list=" + list +
                ", total=" + total +
                '}';
    }
}
