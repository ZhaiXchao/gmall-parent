package com.zxc.gmall.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.pms.entity.Brand;
import com.zxc.gmall.pms.mapper.BrandMapper;
import com.zxc.gmall.pms.service.BrandService;
import com.zxc.gmall.vo.PageInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    /**
     *
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<Brand> name = null;
        //按关键字查询模糊查询,可以没有
        if(!StringUtils.isEmpty(keyword))
            name = new QueryWrapper<Brand>().like("name", keyword);

        IPage<Brand> page = brandMapper.selectPage(new Page<Brand>(pageNum, pageSize), name);

        PageInfoVo vo = new PageInfoVo(page.getTotal(), page.getPages(), pageSize, page.getCurrent(), page.getRecords());
        return vo;
    }
}
