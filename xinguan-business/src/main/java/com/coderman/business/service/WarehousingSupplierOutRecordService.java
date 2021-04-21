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
public interface WarehousingSupplierOutRecordService {



//    /**
//     * 添加
//     * @param warehousingSupplierVO
//     */
//    WarehousingSupplier add(WarehousingSupplierVO warehousingSupplierVO);


    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @param warehousingSupplierReq
     * @return
     */
    PageVO<WarehousingSupplierOutRecordVO> allList(Integer pageNum, Integer pageSize, WarehousingSupplierReq warehousingSupplierReq);



    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @param warehousingSupplierReq
     * @return
     */
    PageVO<WarehousingSupplierOutRecordVO> list(Integer pageNum, Integer pageSize, WarehousingSupplierReq warehousingSupplierReq);



//    /**
//     * 编辑供应商
//     * @param id
//     * @return
//     */
//    SupplierVO edit(Long id);

//    /**
//     * 更新
//     * @param id
//     * @param supplierVO
//     */
//    void update(Long id, SupplierVO supplierVO);

//    /**
//     * 删除
//     * @param id
//     */
//    void delete(Long id);


}
