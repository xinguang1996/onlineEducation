package com.gxg.dao.impl;

import com.gxg.dao.CourseDao;
import com.gxg.dao.rowmapper.CourseRowMapper;
import com.gxg.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 郭欣光
 * @date 2019/1/30 14:45
 */
@Repository(value = "courseDao")
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获得课程数量
     *
     * @return 课程数量
     * @author 郭欣光
     */
    @Override
    public int getCourseCount() {
        String sql = "select count(1) from course";
        int rowCount = jdbcTemplate.queryForObject(sql, Integer.class);
        return rowCount;
    }


    /**
     * 获取指定范围按照修改时间排序的课程
     *
     * @param limitStart 第一个limit
     * @param limitEnd   第二个limit
     * @return 课程列表
     * @author 郭欣光
     */
    @Override
    public List<Course> getCourseByLimitOrderByModifyTime(int limitStart, int limitEnd) {
        String sql = "select * from course order by modify_time desc limit ?, ?";
        List<Course> courseList = jdbcTemplate.query(sql, new CourseRowMapper(), limitStart, limitEnd);
        return courseList;
    }

    /**
     * 获取指定范围按照学习人数排序的课程
     *
     * @param linitStart 第一个limit
     * @param limitEnd   第二个limit
     * @return 课程列表
     * @author 郭欣光
     */
    @Override
    public List<Course> getCourseByLimitOrderByStudyNumber(int linitStart, int limitEnd) {
        String sql = "select * from course order by study_number desc limit ?, ?";
        List<Course> courseList = jdbcTemplate.query(sql, new CourseRowMapper(), linitStart, limitEnd);
        return courseList;
    }

    /**
     * 获取与课程名称或教师名模糊查询的课程个数
     *
     * @param content 要查找的内容
     * @return 课程个数
     * @author 郭欣光
     */
    @Override
    public int getCountByLikeNameOrLikeUserName(String content) {
        content = "%" + content + "%";
        String sql = "select count(1) from (select distinct * from course where course.name like ? or course.user_email in (select user.email from user where user.name like ?)) a";
        int rowCount = jdbcTemplate.queryForObject(sql, Integer.class, content, content);
        return rowCount;
    }

    /**
     * 获取与课程名称或教师名模糊查询匹配及指定范围按照修改时间排序的课程列表
     *
     * @param content       要查找的内容
     * @param limitStart    第一个limit
     * @param limitStartEnd 第二个limit
     * @return 课程列表
     * @author 郭欣光
     */
    @Override
    public List<Course> getCourseByLikeNameOrLikeUserNameAndLimitOrderByTime(String content, int limitStart, int limitStartEnd) {
        content = "%" + content + "%";
        String sql = "select distinct * from course where course.name like ? or course.user_email in (select user.email from user where user like ?) order by course.modify_time desc limit ?, ?";
        List<Course> courseList = jdbcTemplate.query(sql, new CourseRowMapper(), content, content, limitStart, limitStartEnd);
        return courseList;
    }
}
