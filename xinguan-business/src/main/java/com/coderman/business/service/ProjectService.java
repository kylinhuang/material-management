package com.coderman.business.service;


import com.coderman.common.error.BusinessException;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProjectVO;
import com.coderman.common.vo.system.PageVO;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface ProjectService {

    /**
     * 添加项目
     * @param projectVO
     */
    ProjectVO add(ProjectVO projectVO);


    /**
     * 项目列表
     * @param pageNum
     * @param pageSize
     * @param projectVO
     * @return
     */
    PageVO<ProjectVO> findProjectList(Integer pageNum, Integer pageSize, ProjectVO projectVO);



    /**
     * 我的列表
     * @param pageNum
     * @param pageSize
     * @param projectVO
     * @return
     */
    PageVO<ProjectVO> findMyProjectList(Integer pageNum, Integer pageSize, ProjectVO projectVO);




    /**
     * 编辑项目
     * @param id
     * @return
     */
    ProjectVO edit(Long id);


    ProjectVO info(Long id);


    /**
     * 更新项目
     * @param id
     * @param projectVO
     */
    void update(Long id, ProjectVO projectVO);

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
