package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {

    /**
     * 获取所有的广告位
     * @return
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 添加广告位
     * @param promotionSpace
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id 查询广告位信息
     * @param id
     * @return
     */
    public PromotionSpace findPromotionSpaceById(int id);

    /**
     * 修改广告位
     * @param promotionSpace
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);


}
