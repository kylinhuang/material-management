package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Data
public class GoodsInfoVO {

    private Long id;

    private Long userId;

    @NotNull
    private Long productId;

    @NotNull
    private BigDecimal price;



    private String remark;

    private Integer sort;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    private Integer status;//0:删除,1:创建修改,2:审核中,3:审核通过


    private String pNum;

    private String name;

    private String model;

    private String unit;

    private Long oneCategoryId;

    private Long twoCategoryId;

    private Long threeCategoryId;

    private String imageUrl;


    private String merchantUserName;

    private String merchantNickName;

    private String merchantPhoneNumber;

    private String merchant_image_url;

    private BigDecimal proposedPrice;

    private String productRemark;




    private String merchantProvince;
    private String merchantProvinceValue;

    private String merchantCity;
    private String merchantCityValue;

    private String merchantOrigin;
    private String merchantOriginValue;

    private String merchantAddress;


    private Long rejectNumber; //拒收次数
    private Long receiveNumber; //接收次数


}
