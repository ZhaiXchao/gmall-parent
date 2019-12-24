package com.zxc.gmall.ums.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxc.gmall.ums.entity.Admin;
import com.zxc.gmall.ums.mapper.AdminMapper;
import com.zxc.gmall.ums.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-23
 */
//@Service=@Component
@Component
@Service//使用dubbo下的Service注解暴露服务
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String username, String password) {
        //使用Spring加密工具类获取加密后的字符串
        password =  DigestUtils.md5DigestAsHex(password.getBytes());

        //Mybatis使用Wrapper接口查询
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>().eq("username", username).eq("password", password);
        Admin admin = adminMapper.selectOne(queryWrapper);

        return admin;
    }

    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    @Override
    public Admin getUserInfo(String userName) {

        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",userName));

    }
}
