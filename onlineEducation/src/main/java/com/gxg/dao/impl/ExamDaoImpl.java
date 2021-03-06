package com.gxg.dao.impl;

import com.gxg.dao.ExamDao;
import com.gxg.dao.rowmapper.ExamRowMapper;
import com.gxg.entities.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 考试相关数据库信息
 * @author 郭欣光
 * @date 2019/3/21 11:22
 */
@Repository(value = "examDao")
public class ExamDaoImpl implements ExamDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过ID获取考试数量
     *
     * @param id ID
     * @return 考试数量
     * @author 郭欣光
     */
    @Override
    public int getCountById(String id) {
        String sql = "select count(1) from exam where id=?";
        int rowCount = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return rowCount;
    }

    /**
     * 添加考试
     *
     * @param exam 考试信息
     * @return 数据库改变行数
     * @author 郭欣光
     */
    @Override
    public int createExam(Exam exam) {
        String sql = "insert into exam(id, name, requirement, start_time, end_time, duration, create_time, modify_time, course_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int changeCount = jdbcTemplate.update(sql, exam.getId(), exam.getName(), exam.getRequirement(), exam.getStartTime(), exam.getEndTime(), exam.getDuration(), exam.getCreateTime(), exam.getModifyTime(), exam.getCourseId());
        return changeCount;
    }

    /**
     * 根据课程ID获取考试信息个数
     *
     * @param courseId 课程ID
     * @return 考试信息个数
     * @author 郭欣光
     */
    @Override
    public int getCountByCourseId(String courseId) {
        String sql = "select count(1) from exam where course_id=?";
        int rowCount = jdbcTemplate.queryForObject(sql, Integer.class, courseId);
        return rowCount;
    }

    /**
     * 根据课程ID按照修改时间获取指定页面的考试信息
     *
     * @param courseId   课程ID
     * @param limitStart 第一个limit
     * @param limitEnd   第二个limit
     * @return 考试信息
     * @author 郭欣光
     */
    @Override
    public List<Exam> getExamByCourseIdAndLimitOrderByModifyTime(String courseId, int limitStart, int limitEnd) {
        String sql = "select * from exam where course_id=? order by modify_time desc limit ?, ?";
        List<Exam> examList = jdbcTemplate.query(sql, new ExamRowMapper(), courseId, limitStart, limitEnd);
        return examList;
    }

    /**
     * 根据ID获取考试信息
     *
     * @param id 考试ID
     * @return 考试信息
     * @author 郭欣光
     */
    @Override
    public Exam getExamById(String id) {
        String sql = "select * from exam where id=?";
        Exam exam = jdbcTemplate.queryForObject(sql, new ExamRowMapper(), id);
        return exam;
    }

    /**
     * 修改考试信息
     *
     * @param exam 考试信息
     * @return 数据库改变行数
     * @author 郭欣光
     */
    @Override
    public int updateExam(Exam exam) {
        String sql = "update exam set name=?, requirement=?, start_time=?, end_time=?, duration=?, create_time=?, modify_time=?, course_id=? where id=?";
        int changeCount = jdbcTemplate.update(sql, exam.getName(), exam.getRequirement(), exam.getStartTime(), exam.getEndTime(), exam.getDuration(), exam.getCreateTime(), exam.getModifyTime(), exam.getCourseId(), exam.getId());
        return changeCount;
    }

    /**
     * 删除考试
     *
     * @param exam 考试信息
     * @return 数据库改变行数
     * @author 郭欣光
     */
    @Override
    public int deleteExam(Exam exam) {
        String sql = "delete from exam where id=?";
        int changeCount = jdbcTemplate.update(sql, exam.getId());
        return changeCount;
    }

    /**
     * 根据考试所属课程用户邮箱获取考试个数
     *
     * @param userEmail 用户邮箱
     * @return 考试个数
     * @author 郭欣光
     */
    @Override
    public int getCountByCourseUserEmail(String userEmail) {
        String sql = "select count(1) from exam where course_id in (select course.id from course where user_email=?)";
        int rowCount = jdbcTemplate.queryForObject(sql, Integer.class, userEmail);
        return rowCount;
    }

    /**
     * 根据考试所属课程用户邮箱按照修改时间排序获取指定范围的考试信息
     *
     * @param userEmail  用户邮箱
     * @param limitStart 第一个limit
     * @param limitEnd   第二个limit
     * @return 考试信息
     * @author 郭欣光
     */
    @Override
    public List<Exam> getExamByCourseUserEmailAndLimitOrderByModifyTiime(String userEmail, int limitStart, int limitEnd) {
        String sql = "select * from exam where course_id in (select course.id from course where user_email=?) order by modify_time desc limit ?, ?";
        List<Exam> examList = jdbcTemplate.query(sql, new ExamRowMapper(), userEmail, limitStart, limitEnd);
        return examList;
    }


}
