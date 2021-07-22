package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /**
     * 查询所有广告位列表
     * @return
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有广告位成功", allPromotionSpace);

        return responseResult;
    }

    /**
     * 添加&修改广告位
     * @param promotionSpace
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult savePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        if(promotionSpace.getId() == null){

            //新增
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "新增广告位成功", null);

        }else{

            //修改
            promotionSpaceService.updatePromotionSpace(promotionSpace);

            return new ResponseResult(true, 200, "更新广告位成功", null);
        }
    }

    /**
     * 根据id查询 广告位信息
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){

        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        return new ResponseResult(true, 200, "查询具体广告位成功", promotionSpace);
    }



}
