package com.coderman.common.vo.business;

import lombok.Data;

@Data
public class StatGoodsVO {

    private String dateStr; // yyyymm

    private String supplierName;

    private String supplierPhone;

    private String productName;

    private String productModel;

    private Double productNum; //下发数量

    private Double deliveryNum; //发货数量

    private Double warehousingNum; // 入库数量

    private Double minReviewPrice; // 最低审核价

    private Double maxReviewPrice;  // 最高审核价

}


