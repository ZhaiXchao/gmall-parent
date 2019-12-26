package com.zxc.gmall.pms.service;

import com.zxc.gmall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxc.gmall.vo.PageInfoVo;
import com.zxc.gmall.vo.product.PmsProductParam;
import com.zxc.gmall.vo.product.PmsProductQueryParam;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据复杂条件查询商品信息
     * @param productQueryParam
     * @return
     */
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);

    /**
     * 保存商品信息
     * @param productParam
     */
    void saveProductMutilateInfo(PmsProductParam productParam);
}
