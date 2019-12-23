package com.zxc.gmall.pms.service.impl;

import com.zxc.gmall.pms.entity.Product;
import com.zxc.gmall.pms.mapper.ProductMapper;
import com.zxc.gmall.pms.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
