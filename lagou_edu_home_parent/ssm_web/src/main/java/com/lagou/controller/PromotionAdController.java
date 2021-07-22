package com.lagou.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
    分页查询所有广告信息
    */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVo) {

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", pageInfo);

        return responseResult;
    }

    /*
        图片上传
     */
    @RequestMapping("/PromotionAdUpload")
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
     * 新建&修改广告信息
     * @param promotionAd
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        System.out.println("controller接收到的参数位：" + promotionAd);
        System.out.println("------------------------------------------");
        System.out.println(promotionAd.getStatus());

        if (promotionAd.getId() == null) {
            //新建
            Date date = new Date();
            promotionAd.setCreateTime(date);
            promotionAd.setUpdateTime(date);
            promotionAdService.savePromotionAd(promotionAd);

            return new ResponseResult(true, 200, "新建广告信息成功", null);
        } else {
            //修改
            promotionAd.setUpdateTime(new Date());
            promotionAdService.updatePromotionAd(promotionAd);

            return new ResponseResult(true, 200, "修改广告信息成功",  null);
        }

    }

    /**
     * 根据ID查询广告信息成功
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){

        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);

        return new ResponseResult(true, 200, "根据ID查询广告信息成功", promotionAdById);

    }

    /**
     * 广告动态上下线
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(Integer id, Integer status) {

        promotionAdService.updatePromotionAdStatus(id, status);

        return new ResponseResult(true, 200, "广告动态上下线成功", null);

    }


}
