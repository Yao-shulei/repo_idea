package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 查询所有课程信息
     * @return
     */
    public List<Course> findAllCourse();

    /**
     * 多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 新增课程信息
     */
    public void saveCourse(Course course);

    /**
     * 新增讲师信息
     * @param teacher
     */
    public void saveTeacher(Teacher teacher);

    /**
     * 回显课程信息（根据ID查询对应的课程信息及关联的讲师信息）
     * @param id
     * @return
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 修改课程信息
     * */
    public void updateCourse(Course course);

    /**
     * 修改讲师信息
     * */
    public void updateTeacher(Teacher teacher);

    /**
     * 课程状态管理
     * @param course
     */
    public void updateCourseStatus(Course course);

}
