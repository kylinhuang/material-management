package com.coderman.common.model.project;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "biz_project_tasks")
public class ProjectTask {

    @Id
    private Long id;

    private Long projectId;


    private Long productNumber;  // 物资 数量

    private Long deliveryNumber;  // 发货 数量
    private BigDecimal deliveryPrice;  // 发货 价格

    private Long warehousingNumber;  // 入库 数量

    private BigDecimal reviewPrice;  // 审核 价格


    private Long productId;  // 物资id

    private Long goodsId;                 // 商品 id
    private Long supplierId;              // 供应商 id


    private Long warehousingId;            // 仓储 id

    private String invoiceImageUrl;        // 发货单
    private String deliveryImageUrl;        // 发货图片

    private Date createTime;

    private Date modifiedTime;

    private Integer sort;

    private Integer status; //1:创建 ,2:待发货 ,3:已发货 ,4:入库, 5:驳回 , 6:已结算

    private Integer deleteStatus = 1;//是否有效  0:无效   1:有效


}
