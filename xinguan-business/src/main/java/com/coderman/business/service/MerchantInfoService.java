package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.vo.business.MerchantInfoVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProjectVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface MerchantInfoService {



    /**
     * 我的 商户信息
     * @return
     */
    MerchantInfoVO findMerchantInfo();


    /**
     * 添加商户信息
     * @param projectVO
     */
    void add(MerchantInfoVO projectVO);


    /**
     * 项目列表
     * @param pageNum
     * @param pageSize
     * @param projectVO
     * @return
     */
    PageVO<MerchantInfoVO> findList(Integer pageNum, Integer pageSize, MerchantInfoVO projectVO);







    /**
     * 编辑项目
     * @param id
     * @return
     */
    MerchantInfoVO edit(Long id);

    /**
     * 更新
     * @param id
     * @param merchantInfoVO
     */
    void update(Long id, MerchantInfoVO merchantInfoVO);


    /**
     * 更新
     * @param id
     * @param merchantInfoVO
     */
    void updateStatus(Long id, MerchantInfoVO merchantInfoVO);



    /**
     * 删除商品
     * @param id
     */
    void delete(Long id) throws BusinessException;

    /**
     * 项目列表
     * @param pageNum
     * @param pageSize
     * @param projectVO
     * @return
     */
    PageVO<ProductStockVO> findProductStocks(Integer pageNum, Integer pageSize, ProjectVO projectVO);

    /**
     * 所有库存信息
     * @return
     */
    List<ProductStockVO> findAllStocks(Integer pageNum, Integer pageSize, ProjectVO projectVO);

    /**
     * 移入回收站
     * @param id
     */
    void remove(Long id) throws BusinessException;

    /**
     * 从回收站恢复数据
     * @param id
     */
    void back(Long id) throws BusinessException;

    /**
     * 物资添加审核
     * @param id
     */
    void publish(Long id) throws BusinessException;


}
