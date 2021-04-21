package com.coderman.common.vo.business;

import com.coderman.common.vo.system.UserVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
@Data
public class WarehousingSupplierOutRecordVO {
    private Long id;

    // 仓储
    private UserVO warehousing;
    // 仓储 id
    private Long warehousingId;

    //物资
    private ProductVO product;
    //物资id
    private Long productId;


    // 项目id
    private Long projectId;
    //项目名称
    private String projectName;


    private String recipients; //领取人
    private String recipientsPhoneNumber; //领取人电话
    private String recipientsInfo; //领取人部门

    private String whereabouts; //物资去向


    //领取数量
    private Long number;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;


    private Integer sort = 1 ;
    private Integer deleteStatus =1 ;


}
