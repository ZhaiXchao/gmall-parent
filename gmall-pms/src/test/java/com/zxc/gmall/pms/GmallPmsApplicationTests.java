package com.zxc.gmall.pms;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zxc.gmall.pms.entity.Brand;
import com.zxc.gmall.pms.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallPmsApplicationTests {

    @Autowired
    BrandService ba;

    @Autowired//操作String redis
    StringRedisTemplate stringRedisTemplate;

    @Autowired//操作redis
    RedisTemplate<Object,Object> redisTemplate;

    @Test
    public void contextLoads() {
        Brand byId = ba.getById(1);
        System.out.println(byId);
    }

    /**
     * redis默认存对象是通过序列化
     * 改变默认序列化方式为json
     */
    @Test
    public void testRedis() {
//        redisTemplate.opsForHash();//操作哈希
//        redisTemplate.opsForSet();//无序不重复
//        redisTemplate.opsForZSet();//有序不重复
//        redisTemplate.opsForList();//操作list

        redisTemplate.opsForValue().set("name","1231231231");

        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);

    }

}
