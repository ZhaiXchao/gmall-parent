package com.zxc.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.constant.SysCacheConstant;
import com.zxc.gmall.pms.entity.ProductCategory;
import com.zxc.gmall.pms.mapper.ProductCategoryMapper;
import com.zxc.gmall.pms.service.ProductCategoryService;
import com.zxc.gmall.vo.product.PmsProductAttributeCategoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    ProductCategoryMapper categoryMapper;


    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    /**
     * 查询分类(菜单)信息,放入redis缓存中.
     * @param i
     * @return
     */
    @Override
    public List<PmsProductAttributeCategoryItem> listCategoryWithChildren(Integer i) {
        //获取缓存
        Object cacheMenu = redisTemplate.opsForValue().get(SysCacheConstant.CATEGORY_MENU_CACHE_KEY);

        List<PmsProductAttributeCategoryItem> items = null;
        if (cacheMenu != null){
            log.info("命中缓存");
            items = (List<PmsProductAttributeCategoryItem>) cacheMenu;
            return items;
        }

        items = categoryMapper.listCategoryWithChildren(i);
        redisTemplate.opsForValue().set(SysCacheConstant.CATEGORY_MENU_CACHE_KEY,items);

        return items;
    }
}
