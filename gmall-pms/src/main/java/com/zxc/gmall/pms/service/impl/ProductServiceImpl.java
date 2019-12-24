package com.zxc.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.pms.entity.Product;
import com.zxc.gmall.pms.mapper.ProductMapper;
import com.zxc.gmall.pms.service.ProductService;
import com.zxc.gmall.vo.PageInfoVo;
import com.zxc.gmall.vo.product.PmsProductQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    /**
     * 查询商品分页数据
     * @param param
     * @return
     */
    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam param) {

        //实现负责查询
        QueryWrapper<Product> wrapper = new QueryWrapper<>();

        if (param.getBrandId()!=null)
            wrapper.eq("brand_id",param.getBrandId());
        if (!StringUtils.isEmpty(param.getKeyword()))
            wrapper.like("name",param.getKeyword());
        if (param.getProductCategoryId()!=null)
            wrapper.eq("product_category_id",param.getProductCategoryId());
        if (!StringUtils.isEmpty(param.getProductSn()))
            wrapper.like("product_sn",param.getProductSn());
        if (param.getPublishStatus()!=null)
            wrapper.eq("publish_status",param.getPublishStatus());
        if (param.getVerifyStatus()!=null)
            wrapper.eq("verify_status",param.getVerifyStatus());


        //MyBatis-Plus 中提供的分页API
        IPage<Product> iPage = productMapper.selectPage(new Page<Product>(param.getPageNum(), param.getPageSize()), wrapper);

        PageInfoVo vo =
                new PageInfoVo(
                        iPage.getTotal(),//总记录数
                        iPage.getPages(),//总页数
                        param.getPageSize(),       //页面大小
                        iPage.getCurrent(),//当前页码
                        iPage.getRecords());//页面数据

        return vo;
    }
}
