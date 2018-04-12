package com.saltedfish.app.bean.post;

import java.util.List;

public class PostHomeResponseDto {
    private String name;
    private String id;
    private String headImg;
    private String content;
    private Boolean isStar;
    private List<PostHomeResponseInnerDto> imageList;
    private String imageMaxHightPx;
    private String starSum;
    private String contentSum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStar() {
        return isStar;
    }

    public void setStar(Boolean star) {
        isStar = star;
    }

    public List<PostHomeResponseInnerDto> getImageList() {
        return imageList;
    }

    public void setImageList(List<PostHomeResponseInnerDto> imageList) {
        this.imageList = imageList;
    }

    public String getImageMaxHightPx() {
        return imageMaxHightPx;
    }

    public void setImageMaxHightPx(String imageMaxHightPx) {
        this.imageMaxHightPx = imageMaxHightPx;
    }

    public String getStarSum() {
        return starSum;
    }

    public void setStarSum(String starSum) {
        this.starSum = starSum;
    }

    public String getContentSum() {
        return contentSum;
    }

    public void setContentSum(String contentSum) {
        this.contentSum = contentSum;
    }

    @Override
    public String toString() {
        return "PostHomeResponseDto{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", headImg='" + headImg + '\'' +
                ", content='" + content + '\'' +
                ", isStar=" + isStar +
                ", imageList=" + imageList +
                ", imageMaxHightPx='" + imageMaxHightPx + '\'' +
                ", starSum='" + starSum + '\'' +
                ", contentSum='" + contentSum + '\'' +
                '}';
    }
}
