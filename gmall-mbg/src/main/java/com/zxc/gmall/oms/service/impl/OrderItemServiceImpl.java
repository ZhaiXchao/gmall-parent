package com.zxc.gmall.oms.service.impl;

import com.zxc.gmall.oms.entity.OrderItem;
import com.zxc.gmall.oms.mapper.OrderItemMapper;
import com.zxc.gmall.oms.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
