package com.zxc.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.cms.entity.PrefrenceAreaProductRelation;
import com.zxc.gmall.pms.entity.*;
import com.zxc.gmall.pms.mapper.*;
import com.zxc.gmall.pms.service.ProductService;
import com.zxc.gmall.vo.PageInfoVo;
import com.zxc.gmall.vo.product.PmsProductParam;
import com.zxc.gmall.vo.product.PmsProductQueryParam;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    ProductAttributeValueMapper productAttributeValueMapper;

    @Autowired
    ProductFullReductionMapper productFullReductionMapper;

    @Autowired
    SkuStockMapper skuStockMapper;

    @Autowired
    ProductLadderMapper productLadderMapper;
    //多线程共享数据数据,需要考虑安全问题
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    //原理
    private Map<Thread,Long> map = new HashMap<>();



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

    @Override
    @Transactional
    public void saveProductMutilateInfo(PmsProductParam productParam) {

        ProductServiceImpl proxy = (ProductServiceImpl) AopContext.currentProxy();

        //1保存商品基本信息 pms_product
        proxy.saveProduct(productParam);

        //Mybatis自动获取自增长id的值
        Long productId = threadLocal.get();

        //2保存商品属性信息 pms_product_attribute_value
        proxy.saveProductAttribute(productParam);

        //3保存商品满减信息pms_product_category_attribute_relation
        proxy.saveProductFullReduction(productParam);

        //4保存满减等级及其价格
        proxy.saveProductLadder(productParam);

        //5保存Sku库存 pms_sku_stock
        proxy.saveSkuStock(productParam);
    }


    //保存Sku库存 pms_sku_stock
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveSkuStock(PmsProductParam productParam) {
        int i = 10/0;
        List<SkuStock> skuStockList = productParam.getSkuStockList();
        skuStockList.forEach((skuStock)->{
            skuStock.setProductId(threadLocal.get());
            if (StringUtils.isEmpty(skuStock.getSkuCode())){
                skuStock.setSkuCode(skuCode());
            }
            skuStockMapper.insert(skuStock);
        });
    }
    //保存满减等级及其价格
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductLadder(PmsProductParam productParam) {
        List<ProductLadder> productLadderList = productParam.getProductLadderList();
        productLadderList.forEach((productLadder)->{
            productLadder.setProductId(threadLocal.get());
            productLadderMapper.insert(productLadder);
        });
    }
    //保存商品满减信息pms_product_category_attribute_relation
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductFullReduction(PmsProductParam productParam) {
        List<ProductFullReduction> productFullReductionList = productParam.getProductFullReductionList();
        productFullReductionList.forEach((productFullReduction)->{
            productFullReduction.setProductId(threadLocal.get());
            productFullReductionMapper.insert(productFullReduction);
        });
    }
    //保存商品属性信息 pms_product_attribute_value
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductAttribute(PmsProductParam productParam) {
        List<ProductAttributeValue> productAttributeValueList = productParam.getProductAttributeValueList();
        productAttributeValueList.forEach((productAttributeValue)->{
            //插入商品属性值信息             threadLocal.get()
            productAttributeValue.setProductId(map.get(Thread.currentThread()));
            productAttributeValueMapper.insert(productAttributeValue);
        });
    }
    //保存商品基本信息 pms_product
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProduct(PmsProductParam productParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productParam,product);
        productMapper.insert(product);
        //把商品id保存到当前线程中
        threadLocal.set(product.getId());
        //同上
        map.put(Thread.currentThread(),product.getId());
    }

    //获取skucode
    private String skuCode(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String skuCode = dateFormat.format(new Date());
        Long l = System.currentTimeMillis();
        String suffix = l.toString();
        suffix = suffix.substring(suffix.length()-3,suffix.length());
        return threadLocal.get()+skuCode+"_"+suffix;
    }
}
