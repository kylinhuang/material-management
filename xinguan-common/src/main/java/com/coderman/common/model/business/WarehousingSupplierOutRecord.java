package com.coderman.common.model.business;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "biz_warehousing_out_stock")
public class WarehousingSupplierOutRecord {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    // 仓储 id
    private Long warehousingId;

    private Long productId; //物资id
    private Long projectId;// 项目id


    private String recipients; //领取人
    private String recipientsPhoneNumber; //领取人电话
    private String recipientsInfo; //领取人部门

    private Long number; //领取数量
    private String whereabouts; //物资去向

    private Date createTime;
    private Date modifiedTime;

    private Integer sort;
    private Integer deleteStatus ;

}
