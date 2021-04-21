package com.coderman.common.vo.business;

import com.coderman.common.vo.system.UserVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
@Data
public class WarehousingSupplierInRecordVO {
    private Long id;

    // 仓储
    private UserVO warehousing;

    // 供应商
    private UserVO supplier;

    private ProductVO product;
    private Long number;

    private Long projectId;
    private String projectName; //项目名称


    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;


}
