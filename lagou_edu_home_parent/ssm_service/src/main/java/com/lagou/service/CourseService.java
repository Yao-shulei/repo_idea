package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /**
     * 多条件查询课程列表
     * */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     *添加课程及讲师信息
     * @param courseVO
     */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /**
     * 根据ID查询课程信息
     * @param id
     * @return
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 修改课程及教师信息
     * @param courseVO
     */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /**
     * 课程状态变更
     * @param id
     * @param status
     */
    public void updateCourseStatus(int courseid,int status);

}
