package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 查询所有课程信息
     * @return
     */
    @RequestMapping("findAllCourse")
    public ResponseResult findAllCourse(){

        List<Course> courseList = courseService.findAllCourse();

        ResponseResult responseResult = new ResponseResult(true,200,"响应成功",courseList);

        return responseResult;

    }

    /**
     * 查询课程信息&条件查询 接口
     * */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {

        //调用service
        List<Course> list = courseService.findCourseByCondition(courseVO);

        for (Course course : list)
            System.out.println(course);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);

        return responseResult;

    }

    /**
     * 课程图片上传
     * @param file
     * @return
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1、判断接收到的文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //2、获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        //只需要D:\apache-tomcat-8.5.56\webapps\
        String realPath = request.getServletContext().getRealPath("/");
        //D:\apache-tomcat-8.5.56\webapps\
        String webappsPath = realPath.substring(0,realPath.indexOf("ssm_web"));

        //3.获取原文件名
        String fileName = file.getOriginalFilename();

        //4.生成新文件名
        String newFileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));

        //5.上传文件
        String uploadPath = webappsPath+"upload\\";
        File filePath = new File(uploadPath,newFileName);

        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }

        //图片进行真正的上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);

        //"filePath": "http://localhost:8080/upload/1597112871741.JPG"
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;
    }


    /**
     * 新增课程信息及讲师信息
     * 新增课程信息和修改课程信息要写在同一个方法中
     * @return
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId() == null){
            //调用service
            courseService.saveCourseOrTeacher(courseVO);

            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);

            return responseResult;
        } else {
            courseService.updateCourseOrTeacher(courseVO);

            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);

            return responseResult;
        }

    }

    /**
     * 根据id查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){

        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据ID查询课程信息成功", courseVO);

        return responseResult;

    }

    /**
     * 课程状态管理
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam Integer id,@RequestParam Integer status){

        //调用service，传递参数，完成课程状态的变更
        courseService.updateCourseStatus(id, status);

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);

        return responseResult;

    }

}
