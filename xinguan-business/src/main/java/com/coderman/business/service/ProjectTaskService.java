package com.coderman.business.service;



import com.coderman.common.error.BusinessException;
import com.coderman.common.vo.business.PriceAnalysisVO;
import com.coderman.common.vo.business.ProjectAnalysisVO;
import com.coderman.common.vo.business.ProjectTaskVO;
import com.coderman.common.vo.system.PageVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:18
 * @Version 1.0
 **/
public interface ProjectTaskService {
//
//    /**
//     * 添加项目
//     * @param projectVO
//     */
//    void add(ProjectVO projectVO);


    /**
     * 项目列表
     * @param projectId
     * @return
     */
    List<ProjectTaskVO> findTask(Integer projectId);




    /**
     * 项目列表
     * @param projectId
     * @return
     */
    ProjectAnalysisVO projectAnalysis(Long projectId);


    PriceAnalysisVO priceAnalysis(Long productId, Long supplierId, String startDate, String  endDate);


    PageVO<ProjectTaskVO> findMyTaskList(Integer pageNum, Integer pageSize,Integer status,Long projectId);


    PageVO<ProjectTaskVO> taskList(Integer pageNum, Integer pageSize,Integer status,Long projectId,Long supplierId);


//    /**
//     * 我的列表
//     * @param pageNum
//     * @param pageSize
//     * @param projectVO
//     * @return
//     */
//    PageVO<ProjectVO> findMyProjectList(Integer pageNum, Integer pageSize, ProjectVO projectVO);
//
//    /**
//     * 编辑项目
//     * @param id
//     * @return
//     */
//    ProjectVO edit(Long id);
//
    /**
     * 更新项目
     * @param id
     * @param list
     */
    void updateList(Long id, ArrayList<ProjectTaskVO> list);



    /**
     * 更新项目
     * @param taskid
     * @param list
     */
    void editTaskStatus(Long taskid, ProjectTaskVO list) throws BusinessException;




    /**
     * 更新项目
     * @param taskid
     * @param list
     */
    void update(Long taskid, ProjectTaskVO list) throws BusinessException;



    /**
     * 更新项目
     * @param id
     * @param projectTask
     */
    void editTask(Long id, ProjectTaskVO projectTask);



//
//    /**
//     * 删除商品
//     * @param id
//     */
//    void delete(Long id) throws BusinessException;
//
//    /**
//     * 项目列表
//     * @param pageNum
//     * @param pageSize
//     * @param projectVO
//     * @return
//     */
//    PageVO<ProductStockVO> findProductStocks(Integer pageNum, Integer pageSize, ProjectVO projectVO);
//
//    /**
//     * 所有库存信息
//     * @return
//     */
//    List<ProductStockVO> findAllStocks(Integer pageNum, Integer pageSize, ProjectVO projectVO);
//
//    /**
//     * 移入回收站
//     * @param id
//     */
//    void remove(Long id) throws BusinessException;
//
//    /**
//     * 从回收站恢复数据
//     * @param id
//     */
//    void back(Long id) throws BusinessException;
//
//    /**
//     * 物资添加审核
//     * @param id
//     */
//    void publish(Long id) throws BusinessException;


}
