package com.coderman.common.model.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "biz_merchant_goods")
public class Goods {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")//此处加上注解
    private Long id;

    private Long userId;

    private Long productId;

    private BigDecimal price;

    private String merchant_image_url;

    private String remark;

    private Integer sort;

    private Date createTime;

    private Date modifiedTime;

    private Long rejectNumber; //拒收次数
    private Long receiveNumber; //接收次数

    private Integer status;//0:删除,1:创建修改,2:审核中,3:审核通过

}
