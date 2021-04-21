package com.coderman.common.vo.business;

import com.coderman.common.vo.system.UserVO;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
@Data
public class WarehousingSupplierReq {
    private Long id;

    // 仓储 id
    private Long warehousingId;

//    private ProductVO product;
    private String productName;

    private Long number;

    private Long projectId;





    private Integer sort;

    private Integer deleteStatus;


}
