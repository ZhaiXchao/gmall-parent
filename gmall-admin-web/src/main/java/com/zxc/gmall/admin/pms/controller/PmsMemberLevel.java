package com.zxc.gmall.admin.pms.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zxc.gmall.to.CommonResult;
import com.zxc.gmall.ums.entity.MemberLevel;
import com.zxc.gmall.ums.service.MemberLevelService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@ApiModel("会员等级")
@RestController
public class PmsMemberLevel {

    @Reference
    MemberLevelService memberLevelService;

    @ApiOperation("列出所有等级")
    @GetMapping("/memberLevel/list")
    public Object list(@RequestParam(defaultValue = "0") Integer defaultStatus){

        List<MemberLevel> list = memberLevelService.list();

        return new CommonResult().success(list);
    }
}
