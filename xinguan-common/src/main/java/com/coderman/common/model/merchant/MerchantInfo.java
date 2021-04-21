package com.coderman.common.model.merchant;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Table(name = "biz_merchant_information", schema = "xinguan", catalog = "")
public class MerchantInfo {


    @Id
    private Long id;

    private Long userId;

    private String bankAccount; //银行账号

    private String bankName; //银行名称

    private String bankAccountName; //银行账号姓名

    private String companyName; //公司名称

    private String remark; //备注

    private Integer sort;

    private Date createTime;

    private Date modifiedTime;

    private String businessLicenseImgUrl; //营业执照 照片

    private Integer status;//0:创建 ,1:提交审核, 2:审核通过


}
