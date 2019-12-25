package com.zxc.gmall.pms.service;

import com.zxc.gmall.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxc.gmall.vo.product.PmsProductAttributeCategoryItem;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
public interface ProductCategoryService extends IService<ProductCategory> {


    List<PmsProductAttributeCategoryItem> listCategoryWithChildren(Integer i);
}
