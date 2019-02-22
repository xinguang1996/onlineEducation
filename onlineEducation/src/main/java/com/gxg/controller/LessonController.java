package com.gxg.controller;

import com.gxg.entities.Course;
import com.gxg.entities.Lesson;
import com.gxg.entities.User;
import com.gxg.service.CourseService;
import com.gxg.service.LessonService;
import com.gxg.service.MessageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 课时相关请求与响应控制
 * @author 郭欣光
 * @date 2019/2/21 10:35
 */

@Controller
@RequestMapping(value = "/lesson")
public class LessonController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private LessonService lessonService;

    @GetMapping(value = "/create/{courseId}")
    public String createLessonPage(@PathVariable String courseId, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login?next=" + "/lesson/create/" + courseId;
        } else {
            User user = (User)session.getAttribute("user");
            Course course = courseService.getCourseById(courseId);
            if (course == null || course.getUserEmail() == null || !course.getUserEmail().equals(user.getEmail())) {
                model.addAttribute("promptTitle", "404");
                model.addAttribute("promptMessage", "对不起，该页面不存在！");
                return "/prompt/prompt.html";
            }
            model.addAttribute("course", course);
            int unReadMessageCount = messageService.getUnreadMessageCount(user);
            model.addAttribute("unReadMessageCount", unReadMessageCount);
            model.addAttribute("user", user);
            List<Course> courseList = courseService.getUserCourseByTopNumber(user, 5);
            model.addAttribute("myCourseList", courseList);
            if (courseList != null && courseList.size() >= 5) {
                model.addAttribute("hasMoreCourse", "yes");
            }
            List<Lesson> lessonList = lessonService.getLessonListByCourseIdAndTopNumber(courseId, 5);
            model.addAttribute("myLessonList", lessonList);
            if (lessonList != null && lessonList.size() >= 5) {
                model.addAttribute("hasMoreLesson", "yes");
            }
            return "/create_lesson.html";
        }
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public String createLesson(@RequestParam String courseId, @RequestParam String lessonName, @RequestParam String lessonContent, HttpServletRequest request) {
        return lessonService.createLesson(courseId, lessonName, lessonContent, request);
    }

    @GetMapping(value = "/public/detail/{lessonId}")
    public String getPublicLessonPage(@PathVariable String lessonId, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login?next=" + "/lesson/public/detail/" + lessonId;
        } else {
            Lesson lesson = lessonService.getLessonById(lessonId);
            Course course = courseService.getCourseById(lesson.getCourseId());
            if (lesson == null || course == null || "1".equals(course.getIsPrivate())) {
                model.addAttribute("promptTitle", "404");
                model.addAttribute("promptMessage", "对不起，该页面不存在！");
                return "/prompt/prompt.html";
            }
            model.addAttribute("lesson", lesson);
            model.addAttribute("course", course);
            User user = (User)session.getAttribute("user");
            int unReadMessageCount = messageService.getUnreadMessageCount(user);
            model.addAttribute("unReadMessageCount", unReadMessageCount);
            model.addAttribute("user", user);
            List<Course> courseListOrderByModifyTime = courseService.getCourseListByIsPrivateAndTopNumberOrderByModifyTime("0", 5);
            model.addAttribute("courseListOrderByModifyTime", courseListOrderByModifyTime);
            if (courseListOrderByModifyTime != null && courseListOrderByModifyTime.size() > 5) {
                model.addAttribute("hasMoreCourseOrderByModifyTime", "yes");
            }
            List<Lesson> lessonList = lessonService.getLessonListByCourseIdAndTopNumber(lesson.getCourseId(), 5);
            model.addAttribute("lessonListOrderByModifyTime", lessonList);
            if (lessonList != null && lessonList.size() >= 5) {
                model.addAttribute("hasMoreLessonOrderByModifyTime", "yes");
            }
            model.addAttribute("courseType", "public");
            return "/lesson_detail.html";
        }
    }

    @GetMapping(value = "/my/detail/{lessonId}")
    public String myLessonPage(@PathVariable String lessonId, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return "redirect:/user/login?next=" + "/lesson/my/detail/" + lessonId;
        } else {
            Lesson lesson = lessonService.getLessonById(lessonId);
            Course course = courseService.getCourseById(lesson.getCourseId());
            User user = (User)session.getAttribute("user");
            if (lesson == null || course == null || !course.getUserEmail().equals(user.getEmail())) {
                model.addAttribute("promptTitle", "404");
                model.addAttribute("promptMessage", "对不起，该页面不存在！");
                return "/prompt/prompt.html";
            }
            model.addAttribute("lesson", lesson);
            model.addAttribute("course", course);
            int unReadMessageCount = messageService.getUnreadMessageCount(user);
            model.addAttribute("unReadMessageCount", unReadMessageCount);
            model.addAttribute("user", user);
            List<Course> courseList = courseService.getUserCourseByTopNumber(user, 5);
            model.addAttribute("myCourseList", courseList);
            if (courseList != null && courseList.size() >= 5) {
                model.addAttribute("hasMoreCourse", "yes");
            }
            List<Lesson> lessonList = lessonService.getLessonListByCourseIdAndTopNumber(lesson.getCourseId(), 5);
            model.addAttribute("myLessonList", lessonList);
            if (lessonList != null && lessonList.size() >= 5) {
                model.addAttribute("hasMoreLesson", "yes");
            }
            return "/my_lesson_detail.html";
        }
    }
}
