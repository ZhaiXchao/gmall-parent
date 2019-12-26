package com.zxc.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.pms.entity.ProductAttribute;
import com.zxc.gmall.pms.mapper.ProductAttributeMapper;
import com.zxc.gmall.pms.service.ProductAttributeService;
import com.zxc.gmall.vo.PageInfoVo;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Autowired
    ProductAttributeMapper productAttributeMapper;

    @Override
    public PageInfoVo listCategoryAttributePageInfo(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        Object o = AopContext.currentProxy();

        QueryWrapper<ProductAttribute> wrapper = new QueryWrapper<ProductAttribute>().eq("type", type).eq("product_attribute_category_id", cid);

        IPage<ProductAttribute> iPage = productAttributeMapper.selectPage(new Page<ProductAttribute>(pageNum, pageSize), wrapper);

        return PageInfoVo.getVo(iPage,pageSize);
    }
}
