package com.saltedfish.app.bean;

public class SimpleSearch {
    private String content;
    private String categoryContent;
    //是什么方法  时间从后到前? 时间从前到后? 热度? 点击率?
    private String method;
    private int page;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getCategoryContent() {
        return categoryContent;
    }

    public void setCategoryContent(String categoryContent) {
        this.categoryContent = categoryContent;
    }
}
