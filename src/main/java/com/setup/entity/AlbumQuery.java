package com.setup.entity;


public class AlbumQuery {
    private String nameLike;//相册名称
    private String tagLike;//相册类型
    private String userLike;//用户名称  弃用

    public AlbumQuery() {
    }

    public String getNameLike() {
        return nameLike;
    }

    public void setNameLike(String nameLike) {
        this.nameLike = nameLike;
    }

    public String getTagLike() {
        return tagLike;
    }

    public void setTagLike(String tagLike) {
        this.tagLike = tagLike;
    }

    public String getUserLike() {
        return userLike;
    }

    public void setUserLike(String userLike) {
        this.userLike = userLike;
    }


    @Override
    public String toString() {
        return "AlbumQuery{" +
                "nameLike='" + nameLike + '\'' +
                ", tagLike='" + tagLike + '\'' +
                ", userLike='" + userLike + '\'' +
                '}';
    }
}
