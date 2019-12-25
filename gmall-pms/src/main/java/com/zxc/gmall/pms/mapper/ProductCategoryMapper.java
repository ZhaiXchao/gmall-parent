package com.zxc.gmall.pms.mapper;

import com.zxc.gmall.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxc.gmall.vo.product.PmsProductAttributeCategoryItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    List<PmsProductAttributeCategoryItem> listCategoryWithChildren(Integer i);
}
