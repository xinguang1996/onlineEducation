package com.gxg.service;

import com.gxg.entities.Lesson;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课时相关业务处理接口
 * @author 郭欣光
 * @date 2019/2/19 10:24
 */

public interface LessonService {

    /**
     * 获取指定课程指定页数的课时信息
     * @param courseId 课程ID
     * @param lessonPage 页数
     * @return 课时相关信息
     * @author 郭欣光
     */
    JSONObject getLessonListByCourseId(String courseId, String lessonPage);

    /**
     * 获取指定课程ID的前N个课时信息
     * @param courseId 课程ID
     * @param topNumber N
     * @return 课时信息
     * @author 郭欣光
     */
    List<Lesson> getLessonListByCourseIdAndTopNumber(String courseId, int topNumber);

    /**
     * 创建课时
     * @param courseId 课程ID
     * @param lessonName 课时名称
     * @param lessonContent 课时内容
     * @param request 用户请求信息
     * @return 处理结果
     * @author 郭欣光
     */
    String createLesson(String courseId, String lessonName, String lessonContent, HttpServletRequest request);
}