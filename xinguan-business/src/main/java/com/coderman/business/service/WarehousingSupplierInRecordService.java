package com.coderman.business.service;


import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface WarehousingSupplierInRecordService {



    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @param warehousingSupplierReq
     * @return
     */
    PageVO<WarehousingSupplierInRecordVO> allList(Integer pageNum, Integer pageSize, WarehousingSupplierReq warehousingSupplierReq);



    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @param warehousingSupplierReq
     * @return
     */
    PageVO<WarehousingSupplierInRecordVO> list(Integer pageNum, Integer pageSize, WarehousingSupplierReq warehousingSupplierReq);



}
