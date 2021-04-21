package com.coderman.business.service;

import com.coderman.common.vo.business.StatGoodsVO;

import java.util.Date;
import java.util.List;

public interface DataStatService {


    /**
     *
     * @param start
     * @param end
     * @param supplier
     * @param productId
     * @return
     */
    List<StatGoodsVO> statGoodsData(Date start, Date end, String productId, String supplier);
}
