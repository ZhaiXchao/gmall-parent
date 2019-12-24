package com.zxc.gmall.pms.service;

import com.zxc.gmall.pms.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxc.gmall.vo.PageInfoVo;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
public interface BrandService extends IService<Brand> {

    PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
