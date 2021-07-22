package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /**
     * 根据课程ID查询对应的课程内容（章节+课时）
     * @param courseId
     * @return
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 回显章节对应的课程信息
     * @param courseId
     * @return
     */
    public Course findCourseByCourseId(int courseId);

    /**
     * 新增章节信息
     * @param courseSection
     */
    public void saveSection(CourseSection courseSection);

    /**
     * 更新章节信息
     * @param courseSection
     */
    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     * @param id
     * @param status
     */
    public void updateSectionStatus(int id,int status);

    /**
     * 新增课时信息
     * @param courseLesson
     */
    public void saveLesson(CourseLesson courseLesson);
}
