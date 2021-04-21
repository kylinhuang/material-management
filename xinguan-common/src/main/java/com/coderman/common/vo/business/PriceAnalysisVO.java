package com.coderman.common.vo.business;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Data
public class PriceAnalysisVO {

    private BigDecimal maxPrice  ;  //  价格

    private BigDecimal minPrice ;

    private Long maxProductNumber  ;  //  数量

    List<ProjectTaskVO> list;

}
