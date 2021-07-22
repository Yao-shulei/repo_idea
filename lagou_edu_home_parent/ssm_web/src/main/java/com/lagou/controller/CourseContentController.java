package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 查询课程内容
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam Integer courseId) {

        //调用service
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        //封装数据并返回
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;
    }

    /**
     * 回显章节对应的课程信息
     * @param courseId
     * @return
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId) {

        //调用service
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", course);
        return responseResult;

    }

    /**
     * 新增&跟新章节信息
     * @param courseSection
     * @return
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection) {

        //判断是否携带章节ID
        if (courseSection.getId() == null){
            //新增
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true, 200, "新增章节成功", null);
        } else {
            //更新
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true, 200, "更新章节成功", null);

        }

    }

    /**
     * 修改章节状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id, int status) {

        courseContentService.updateSectionStatus(id,status);

        //封装最新的状态信息
        Map<Object, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节状态成功", map);
        return responseResult;

    }

    /**
     * 保存课时信息
     * @param courseLesson
     * @return
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){

        courseContentService.saveLesson(courseLesson);
        return new ResponseResult(true, 200, "保存课时信息成功", null);

    }

}
