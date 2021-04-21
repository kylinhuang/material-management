package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.model.business.Supplier;
import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface WarehousingSupplierService {


    /**
     * 列表
     *
     * @param productId
     *
     * @return
     */
    PageVO<SupplierSelectVO> selectList(Long productId);



    /**
     * 添加
     * @param warehousingSupplierVO
     */
    WarehousingSupplier add(WarehousingSupplierVO warehousingSupplierVO);

    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @param supplierVO
     * @return
     */
    PageVO<SupplierVO> findSupplierList(Integer pageNum, Integer pageSize, SupplierVO supplierVO);


    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @param warehousingSupplierReq
     * @return
     */
    PageVO<WarehousingSupplierVO> allList(Integer pageNum, Integer pageSize, WarehousingSupplierReq warehousingSupplierReq);



    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @param warehousingSupplierReq
     * @return
     */
    PageVO<WarehousingSupplierVO> list(Integer pageNum, Integer pageSize, WarehousingSupplierReq warehousingSupplierReq);



    /**
     * 编辑供应商
     * @param id
     * @return
     */
    SupplierVO edit(Long id);

    /**
     * 领取
     * @param id
     * @param supplierVO
     */
    void receive(Long id, WarehousingSupplierOutRecordVO supplierVO) throws BusinessException;

    /**
     * 删除供应商
     * @param id
     */
    void delete(Long id);

    /**
     * 查询所有供应商
     * @return
     */
    List<SupplierVO> findAll();

}
