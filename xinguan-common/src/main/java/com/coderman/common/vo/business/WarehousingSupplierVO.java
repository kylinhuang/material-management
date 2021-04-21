package com.coderman.common.vo.business;

import com.coderman.common.vo.system.UserVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
@Data
public class WarehousingSupplierVO {
    private Long id;

    // 仓储
    private UserVO warehousing;

    private ProductVO product;
    private Long number;

    private Long projectId;
    private String projectName; //项目名称


}
