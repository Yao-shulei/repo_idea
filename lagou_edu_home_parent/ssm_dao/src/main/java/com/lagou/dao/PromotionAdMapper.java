package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
     * 分页查询广告信息
     * @return
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /**
     * 新建广告
     * @param promotionAd
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告
     * @param promotionAd
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     * @param id
     * @return
     */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 广告动态上下线
     * @param promotionAd
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

}
