package com.coderman.common.vo.business;

import com.coderman.common.model.business.Product;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.system.UserVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Data
public class ProjectTaskVO {

    private Long id;

    private Long projectId;
    private String projectName; //项目名称



    private ProductVO product;

    private Long productNumber;  // 物资 数量

    private Long deliveryNumber;  // 发货 数量
    private BigDecimal deliveryPrice;  // 发货 价格


    private Long warehousingNumber;  // 入库 数量
    private BigDecimal reviewPrice;  // 审核 价格
    private BigDecimal totalPrice;  // 总价


    private SupplierSelectVO supplier;

    private UserVO warehousing;

    private Long supplierId;              // 供应商 id

    private String invoiceImageUrl;        // 发货单
    private String deliveryImageUrl;        // 发货图片



    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    private Integer sort;

    private Integer status; //0:删除,1:创建 ,2:待发货 ,3:已发货 ,4:入库, 5:驳回 , 6:价格已确认

    private Integer deleteStatus = 1;//是否有效  0:无效   1:有效


}
