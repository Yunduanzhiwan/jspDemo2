package com.yefeng.experiment21.model;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName ForumBean.java
 * @Description TODO 论坛
 * @createTime 2022年06月07日 10:26:00
 */
public class ForumBean {
    private int Id;
    private String name;
    private String createDate;


    @Override
    public String toString() {
        return "ForumBean{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
