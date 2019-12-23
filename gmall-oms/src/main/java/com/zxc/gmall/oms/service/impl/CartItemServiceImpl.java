package com.zxc.gmall.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.oms.entity.CartItem;
import com.zxc.gmall.oms.mapper.CartItemMapper;
import com.zxc.gmall.oms.service.CartItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

}
