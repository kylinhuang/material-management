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
public class SupplierSelectVO {

    private Long id;


    private Long goodsId;

    private String username;//用户名

    private String nickname;//昵称

    private String email;// 邮箱

    private String phoneNumber; //电话号码

    private Integer status;

    //是否在黑名单
    private Integer inBlackList;

    private String province; //地址 省
    private String provinceValue; //地址 省 code


    private String city; //地址 市

    private String cityValue;//地址 市 code


    private String origin;//地址 区
    private String originValue; //地址 区 code

    private String address; //详细地址

    private Long roleId;

    private Long productId;

    private BigDecimal price;



    private String remark;

    private Integer sort;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

//    private Integer status;//0:删除,1:创建修改,2:审核中,3:审核通过


    private String pNum;

    private String productName;

    private String model;

    private String unit;

    private Long oneCategoryId;

    private Long twoCategoryId;

    private Long threeCategoryId;

    private String imageUrl;

    private String merchant_image_url;

    private BigDecimal proposedPrice;

    private String productRemark;


    private Long rejectNumber; //拒收次数
    private Long receiveNumber; //接收次数


}
