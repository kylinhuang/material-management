package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Data
public class MerchantInfoVO {

    private Long id;

    private Long userId; //用户id

    @NotBlank
    private String companyName; //公司名称

    @NotBlank
    private String remark; //备注

//    @NotBlank
    private String businessLicenseImgUrl; //营业执照 照片

    @NotBlank
    private String bankAccount; //银行账号

    @NotBlank
    private String bankName; //银行名称


    @NotBlank
    private String bankAccountName; //银行账号姓名


    private Integer sort;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;


    private Integer status;//0:创建 ,1:提交审核, 2:审核通过



    private String username;
    private String nickname;
    private String email;
    private String phoneNumber;


    private String province;
    private String provinceValue;


    private String city;
    private String cityValue;

    private String origin;
    private String originValue;

    private String address;

    private Boolean inBlackList;




}
