package com.zxc.gmall.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页数据类型")
@Data
public class PageInfoVo implements Serializable {

    @ApiModelProperty("总记录数")
    private Long total;

    @ApiModelProperty("总页数")
    private Long totalPage;

    @ApiModelProperty("页面大小")
    private Integer pageSize;

    @ApiModelProperty("当前页码")
    private Long pageNum;

    @ApiModelProperty("页面数据")
    private List<? extends Object> list;


    /**
     * 分页工具类
     * @param iPage
     * @param pageSize
     * @return
     */
    public static PageInfoVo getVo(IPage iPage, Integer pageSize){
        return new PageInfoVo(iPage.getTotal(),iPage.getPages(),pageSize,iPage.getCurrent(),iPage.getRecords());
    }
}
