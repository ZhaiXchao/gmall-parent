package com.zxc.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.pms.entity.ProductAttributeCategory;
import com.zxc.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.zxc.gmall.pms.service.ProductAttributeCategoryService;
import com.zxc.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {
    @Autowired
    ProductAttributeCategoryMapper productAttributeCategoryMapper;

    @Override
    public PageInfoVo listCategoryProductAttributePage(Integer pageNum, Integer pageSize) {

        IPage<ProductAttributeCategory> iPage = productAttributeCategoryMapper.selectPage(new Page<ProductAttributeCategory>(pageNum.longValue(), pageSize.longValue()), null);

        return PageInfoVo.getVo(iPage,pageSize);
    }
}
