package com.coderman.common.model.business;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "biz_warehousing_in_stock")
public class WarehousingSupplierInRecord {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    // 仓储 id
    private Long warehousingId;

    private Long productId;

    private Long number;

    // 项目id
    private Long projectId;

    // 商品 id
    private Long goodsId;

    // 供应商 id
    private Long supplierId;

    private Date createTime;
    private Date modifiedTime;

    private Integer sort;
    private Integer deleteStatus ;

}
