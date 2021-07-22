package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

public interface PromotionAdService {

    /*
       分页查询广告信息
    */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

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
     * @param id
     * @param status
     */
    public void updatePromotionAdStatus(int id, int status);


}
