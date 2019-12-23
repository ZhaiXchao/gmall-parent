package com.zxc.gmall.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zxc.gmall.pms.entity.Brand;
import com.zxc.gmall.pms.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallPmsApplicationTests {

    @Autowired
    BrandService ba;

    @Test
    public void contextLoads() {
        Brand byId = ba.getById(1);
        System.out.println(byId);
    }

}
