package com.coderman.common.model.business;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "biz_product")
public class Product {

    @Id
    private Long id;

    private Long userId;

    private String pNum;

    private String name;

    private String model;

    private String unit;

    private BigDecimal proposedPrice;

    private String remark;

    private Integer sort;

    private Date createTime;

    private Date modifiedTime;

    private Long oneCategoryId;

    private Long twoCategoryId;

    private Long threeCategoryId;

    private String imageUrl;

    private Integer status;   // 0:逻辑删除,1:创建,2:审核中 ,3:审核通过


}
