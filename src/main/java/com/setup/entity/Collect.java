package com.setup.entity;

public class Collect {
    private Integer id;
    private Integer uid;
    private Integer aid;

    public Collect() {
    }

    public Collect(Integer uid, Integer aid) {
        this.uid = uid;
        this.aid = aid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "collect{" +
                "id=" + id +
                ", uid=" + uid +
                ", aid=" + aid +
                '}';
    }
}
