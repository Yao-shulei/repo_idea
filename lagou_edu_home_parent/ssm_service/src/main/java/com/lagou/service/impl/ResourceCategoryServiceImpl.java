package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /**
     * 资源分类信息查询
     * @return
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;

    }

    /**
     * 添加资源分类
     * @param resourceCategory
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        resourceCategoryMapper.saveResourceCategory(resourceCategory);

    }

    /**
     * 修改资源分类
     * @param resourceCategory
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        resourceCategoryMapper.updateResourceCategory(resourceCategory);

    }

    /**
     * 删除资源分类
     * @param id
     */
    @Override
    public void deleteResourceCategory(Integer id) {

        resourceCategoryMapper.deleteResourceCategory(id);

    }
}
