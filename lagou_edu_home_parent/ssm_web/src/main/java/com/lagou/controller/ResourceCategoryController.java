package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 作业1.1
     * 资源分类信息查询
     * @return
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult finaAllResourceCategory(){

        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true, 200, "查询所有分类信息成功", allResourceCategory);

    }

    /**
     * 作业1.2
     * 添加&修改资源分类
     * @param resourceCategory
     * @return
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){

        //判读操作类型：添加&修改
        if (resourceCategory.getId() == null){
            //添加操作
            //补全信息
            Date date = new Date();
            resourceCategory.setCreatedTime(date);
            resourceCategory.setUpdatedTime(date);
            resourceCategory.setCreatedBy("system");
            resourceCategory.setUpdatedBy("system");
            //调用service的添加方法
            resourceCategoryService.saveResourceCategory(resourceCategory);
            //返回信息
            ResponseResult responseResult = new ResponseResult(true, 200, "添加资源分类成功", null);

            return responseResult;
        } else {
            //修改操作
            //补全信息
            resourceCategory.setUpdatedTime(new Date());
            resourceCategory.setUpdatedBy("homeworkupdate");
            //调用service的修改方法
            resourceCategoryService.updateResourceCategory(resourceCategory);
            //返回信息
            ResponseResult responseResult = new ResponseResult(true, 200, "修改资源分类成功", null);
            return responseResult;
        }

    }

    /**
     * 作业1.3
     * 根据ID删除资源分类信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){

        resourceCategoryService.deleteResourceCategory(id);

        return new ResponseResult(true, 200, "根据ID删除资源分类信息成功", null);

    }


}
