package com.zxc.gmall.pms.service;

import com.zxc.gmall.pms.entity.ProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxc.gmall.vo.PageInfoVo;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
public interface ProductAttributeService extends IService<ProductAttribute> {

    PageInfoVo listproductAttributePageInfo(Long cid, Integer type, Integer pageSize, Integer pageNum);
}
