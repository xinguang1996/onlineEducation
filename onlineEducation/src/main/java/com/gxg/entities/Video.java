package com.gxg.entities;

import java.sql.Timestamp;

/**
 * 课时视频相关信息
 * @author 郭欣光
 * @date 2019/3/5 10:20
 */
public class Video {

    private String id;

    private String name;

    private String path;

    private Timestamp createTime;

    private String lessonId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", createTime=" + createTime +
                ", lessonId='" + lessonId + '\'' +
                '}';
    }
}
