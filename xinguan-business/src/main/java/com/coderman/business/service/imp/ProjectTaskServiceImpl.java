package com.coderman.business.service.imp;

import com.coderman.business.converter.ProjectTaskConverter;
import com.coderman.business.mapper.*;
import com.coderman.business.service.ProjectTaskService;
import com.coderman.common.enums.system.DeleteStatusEnum;
import com.coderman.common.enums.system.ProjectTaskStatusEnum;
import com.coderman.common.enums.system.RoleEnum;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.business.Goods;
import com.coderman.common.model.business.ProductStock;
import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.model.business.WarehousingSupplierInRecord;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.project.ProjectTask;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:19
 * @Version 1.0
 **/
@Service
@Transactional
public class ProjectTaskServiceImpl implements ProjectTaskService {


    @Autowired
    private ProjectTaskMapper projectTaskMapper;


    @Autowired
    private ProjectTaskConverter projectTaskConverter;


    @Autowired
    private ProjectMapper projectMapper;


    @Autowired
    private WarehousingSupplierMapper warehousingSupplierMapper;


    @Autowired
    private WarehousingSupplierInRecordMapper warehousingSupplierInRecordMapper;


    @Autowired
    private GoodsMapper goodsMapper;


    /**
     * 项目列表
     *
     * @param projectId
     * @return
     */
    @Override
    public List<ProjectTaskVO> findTask(Integer projectId) {

        Example o = new Example(ProjectTask.class);
        Example.Criteria criteria = o.createCriteria();

        criteria.andEqualTo("projectId", projectId);
        o.setOrderByClause("sort asc");
        List<ProjectTask> projectTask = projectTaskMapper.selectByExample(o);
        if (null != projectTask && projectTask.size() > 0) {
            List<ProjectTaskVO> list = projectTaskConverter.converterToVOList(projectTask);
            return list;
        }

        return new ArrayList<ProjectTaskVO>();
    }

    @Override
    public ProjectAnalysisVO projectAnalysis(Long projectId) {

        ProjectAnalysisVO projectAnalysisVO = new ProjectAnalysisVO();
        ArrayList<ChartData>  ChartDataList = new ArrayList< ChartData>();
        for (ProjectTaskStatusEnum itemEnum : ProjectTaskStatusEnum.values()) {
            if ( 0 != itemEnum.getId()){

                ChartData chartData =  new ChartData();

                Example o1 = new Example(ProjectTask.class);
                Example.Criteria criteria1 = o1.createCriteria();
                criteria1.andEqualTo("projectId", projectId);
                o1.setOrderByClause("sort asc");
                criteria1.andEqualTo("status", itemEnum.getId());
                List<ProjectTask> createTask = projectTaskMapper.selectByExample(o1);

                chartData.setName(itemEnum.getMessgae());

                if (null != createTask){
                    chartData.setValue(createTask.size());
                    chartData.setList(createTask);
                }else {
                    chartData.setValue(0);
                    chartData.setList(new ArrayList<>());
                }

                ChartDataList.add(chartData);
            }
        }

        projectAnalysisVO.setChartData(ChartDataList);

        projectAnalysisVO.setProjectId(projectId);
        projectAnalysisVO.setId(projectId);


        return projectAnalysisVO;
    }

    @Override
    public PriceAnalysisVO priceAnalysis(Long productId, Long supplierId, String startDate, String endDate) {

        PriceAnalysisVO eitity = new PriceAnalysisVO();

        Example o = new Example(ProjectTask.class);
        Example.Criteria criteria = o.createCriteria();

        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("supplierId", supplierId);
        criteria.andEqualTo("status", ProjectTaskStatusEnum.audit_price.getId());


        if (!StringUtils.isEmpty(startDate))
            criteria.andGreaterThanOrEqualTo("createTime", startDate);


        if (!StringUtils.isEmpty(endDate))
            criteria.andLessThanOrEqualTo("createTime", endDate);


        o.setOrderByClause("sort asc");

        List<ProjectTask> projectTask = projectTaskMapper.selectByExample(o);
        if (null != projectTask && projectTask.size() > 0) {
            List<ProjectTaskVO> list = projectTaskConverter.converterToVOList(projectTask);
            eitity.setList(list);

            for (ProjectTaskVO item : list){

                if (null == eitity.getMaxPrice()){
                    eitity.setMaxPrice(item.getReviewPrice());
                }else {
                    if (null != item.getReviewPrice()){
                        int compareMax = eitity.getMaxPrice().compareTo(item.getReviewPrice());
                        if (compareMax < 0){ // 小于
                            eitity.setMaxPrice(item.getReviewPrice());
                        }
                    }
                }

                if (null == eitity.getMinPrice()){
                    eitity.setMinPrice(item.getReviewPrice());
                }else {
                    if (null != item.getReviewPrice()) {
                        int compareMin = eitity.getMinPrice().compareTo(item.getReviewPrice());
                        if (compareMin > 0){ // 小于
                            eitity.setMinPrice(item.getReviewPrice());
                        }
                    }
                }


                if (null == eitity.getMaxProductNumber()){
                    eitity.setMaxProductNumber(item.getProductNumber());
                }else {
                    if (item.getProductNumber() > eitity.getMaxProductNumber()){
                        eitity.setMaxProductNumber(item.getProductNumber());
                    }
                }


            }
        }

        return eitity;
    }


    /**
     * 我的项目列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageVO<ProjectTaskVO> findMyTaskList(Integer pageNum, Integer pageSize, Integer status,Long projectId) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(ProjectTask.class);
        Example.Criteria criteria = o.createCriteria();
        if (null == status || -1 == status.intValue()) {
            criteria.andGreaterThan("status", 0);
        } else {
            criteria.andEqualTo("status", status);
        }

        if (null != projectId ) {
            criteria.andEqualTo("projectId", projectId);
        }

        criteria.andEqualTo("deleteStatus", DeleteStatusEnum.normal);

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();

        // 查询创建
        if (activeUser.getUser().getRoleId() == RoleEnum.supplier.getId()) { //供应商
            criteria.andEqualTo("supplierId", userId);
        } else if (activeUser.getUser().getRoleId() == RoleEnum.WarehouseManagement.getId()) { //仓储管理
            criteria.andEqualTo("warehousingId", userId);
        }

        o.setOrderByClause("sort asc");

        List<ProjectTask> projectTaskList = projectTaskMapper.selectByExample(o);
        List<ProjectTaskVO> categoryVOS = projectTaskConverter.converterToVOList(projectTaskList);
        PageInfo<ProjectTask> info = new PageInfo<ProjectTask>(projectTaskList);
        return new PageVO<>(info.getTotal(), categoryVOS);

    }


    /**
     * 我的项目列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageVO<ProjectTaskVO> taskList(Integer pageNum, Integer pageSize, Integer status,Long projectId,Long supplierId) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(ProjectTask.class);
        Example.Criteria criteria = o.createCriteria();

        if ( null == status || -1 == status.intValue()) {
            criteria.andGreaterThan("status", 0);
        } else {
            criteria.andEqualTo("status", status);
        }

        if ( null != projectId ) {
            criteria.andEqualTo("projectId", projectId);
        }

        if ( null != supplierId ) {
            criteria.andEqualTo("supplierId", supplierId);
        }

        criteria.andEqualTo("deleteStatus", DeleteStatusEnum.normal);
        o.setOrderByClause("sort asc");

        List<ProjectTask> projectTaskList = projectTaskMapper.selectByExample(o);
        List<ProjectTaskVO> categoryVOS = projectTaskConverter.converterToVOList(projectTaskList);
        PageInfo<ProjectTask> info = new PageInfo<ProjectTask>(projectTaskList);
        return new PageVO<>(info.getTotal(), categoryVOS);

    }


    public void add(ProjectTaskVO projectTaskVO) {
        ProjectTask projectTask = new ProjectTask();
        BeanUtils.copyProperties(projectTaskVO, projectTask);

        projectTask.setCreateTime(new Date());
        projectTask.setModifiedTime(new Date());

        projectTask.setProjectId(projectTaskVO.getProjectId());

        if (null != projectTaskVO.getProduct()) {
            projectTask.setProductId(projectTaskVO.getProduct().getId());
        } else {
            projectTask.setProductId(0L);
        }

        if (null != projectTaskVO.getSupplier()) {
//            projectTask.setGoodsId(projectTaskVO.getGoods().getId());
            projectTask.setSupplierId(projectTaskVO.getSupplier().getId());
        } else {
            projectTask.setGoodsId(0L);
            projectTask.setSupplierId(0L);
        }

        if (null != projectTaskVO.getWarehousing()) {
            projectTask.setWarehousingId(projectTaskVO.getWarehousing().getId());
        } else {
            projectTask.setWarehousingId(0L);
        }

        projectTask.setStatus(ProjectTaskStatusEnum.create.getId());

        projectTaskMapper.insert(projectTask);

    }
//
//    /**
//     * 编辑商品
//     * @param id
//     * @return
//     */
//    @Override
//    public ProjectVO edit(Long id) {
//        Project project = projectMapper.selectByPrimaryKey(id);
//        return projectConverter.converterToProjectVO(project);
//    }
//

    /**
     * 更新项目
     * @param id
     * @param list
     */
    @Override
    public void updateList(Long id, ArrayList<ProjectTaskVO> list) {
        Project project = projectMapper.selectByPrimaryKey(id);

        if (null == project) return;

        Long allocatorId = project.getAllocatorId();

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (activeUser.getUser().getId().longValue() == allocatorId.longValue()) {
            for (ProjectTaskVO entity : list) {

                if (null == entity.getId() || 0 == entity.getId()) {
                    // add
                    entity.setProjectId(id);

                    if (null != entity.getProduct() && null != entity.getProduct().getId()) {
                        add(entity);
                    }
                } else {
                    entity.setProjectId(id);

                    ProjectTask projectTask = new ProjectTask();
                    BeanUtils.copyProperties(entity, projectTask);
                    projectTask.setModifiedTime(new Date());

                    projectTask.setProjectId(entity.getProjectId());

                    if (null != entity.getProduct()) {
                        projectTask.setProductId(entity.getProduct().getId());
                    } else {
                        projectTask.setProductId(0L);

                    }

                    if (null != entity.getSupplier()) {
                        projectTask.setGoodsId(entity.getSupplier().getGoodsId());
                        projectTask.setSupplierId(entity.getSupplier().getId());
                    } else {
                        projectTask.setGoodsId(0L);
                        projectTask.setSupplierId(0L);
                    }

                    if (null != entity.getWarehousing()) {
                        projectTask.setWarehousingId(entity.getWarehousing().getId());
                    } else {
                        projectTask.setWarehousingId(0L);
                    }

                    projectTaskMapper.updateByPrimaryKey(projectTask);

                }
            }
        } else {
            //TODO  无权操作
            throw new UnauthorizedException();
        }
    }


    @Override
    public void update(Long taskid, ProjectTaskVO entity) throws BusinessException{
        ProjectTask task = projectTaskMapper.selectByPrimaryKey(taskid);
        if (null == task) return ;
        updateStatus(entity);
    }


    @Override
    public void editTaskStatus(Long taskid, ProjectTaskVO entity) throws BusinessException {

        ProjectTask task = projectTaskMapper.selectByPrimaryKey(taskid);

        if (null == task) return ;

        Long supplierId = task.getSupplierId();
        Long goodsId = entity.getSupplier().getGoodsId();
        Long warehousingId = task.getWarehousingId();

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        if (activeUser.getUser().getId().longValue() == supplierId.longValue()){  // 供应商

            if (entity.getStatus() == ProjectTaskStatusEnum.shipped.getId()){

                if (StringUtils.isEmpty(entity.getDeliveryNumber()) || StringUtils.isEmpty(entity.getDeliveryPrice()) || StringUtils.isEmpty(entity.getDeliveryImageUrl()) ){

                    throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR);

                }else {
                    updateStatus(entity);
                }
            }

        }else  if (activeUser.getUser().getId().longValue() == warehousingId.longValue()) {  //仓储


            if (entity.getStatus() == ProjectTaskStatusEnum.warehousing.getId()){

                if (StringUtils.isEmpty(entity.getWarehousingNumber()) || StringUtils.isEmpty(entity.getInvoiceImageUrl())  ){
                    throw  new BusinessException(BusinessCodeEnum.PARAMETER_ERROR);
                }
            }

            updateStatus(entity);

            if (activeUser.getUser().getId().longValue() == warehousingId.longValue()) { //仓储 入库

                if (4 == entity.getStatus().intValue()) { //入库

                    Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                    Long receiveNumber = goods.getReceiveNumber();
                    if (null == receiveNumber) {
                        goods.setReceiveNumber(1L);
                    } else {
                        goods.setReceiveNumber(receiveNumber + 1);
                    }
                    goods.setModifiedTime(new Date());
                    goodsMapper.updateByPrimaryKey(goods);




                    // 更新 库存


                    // 1 查询 是否 存在
                    Example o = new Example(WarehousingSupplier.class);
                    Example.Criteria criteria = o.createCriteria();
                    String codeUnique = entity.getWarehousing().getId()+ "_" + entity.getProduct().getId() + "_"+ entity.getProjectId();

                    criteria.andEqualTo("codeUnique", codeUnique);
                    criteria.andEqualTo("deleteStatus", DeleteStatusEnum.normal);
                    o.setOrderByClause("sort asc");

                    List<WarehousingSupplier> warehousingSupplierList = warehousingSupplierMapper.selectByExample(o);

                    if (null == warehousingSupplierList || warehousingSupplierList.size()==0){

                        // insert
                        WarehousingSupplier warehousingSupplier = new WarehousingSupplier();
                        warehousingSupplier.setWarehousingId(task.getWarehousingId());
                        warehousingSupplier.setProductId(task.getProductId());
                        warehousingSupplier.setNumber(entity.getWarehousingNumber());

                        warehousingSupplier.setProjectId(task.getProjectId());
                        warehousingSupplier.setCreateTime(new Date());
                        warehousingSupplier.setModifiedTime(new Date());
                        warehousingSupplier.setSort(1);
                        warehousingSupplier.setDeleteStatus(DeleteStatusEnum.normal);
                        warehousingSupplier.setCodeUnique(codeUnique);

                        warehousingSupplierMapper.insert(warehousingSupplier);

                    }else {
                        WarehousingSupplier  warehousingSupplier = warehousingSupplierList.get(0);
                        warehousingSupplier.setNumber(warehousingSupplier.getNumber()+entity.getWarehousingNumber());
                        warehousingSupplierMapper.updateByPrimaryKey(warehousingSupplier);
                    }

                    //  更新 入库 记录

                    WarehousingSupplierInRecord warehousingSupplierInRecord = new WarehousingSupplierInRecord();
                    warehousingSupplierInRecord.setWarehousingId(task.getWarehousingId());
                    warehousingSupplierInRecord.setProductId(task.getProductId());
                    warehousingSupplierInRecord.setNumber(entity.getWarehousingNumber());

                    warehousingSupplierInRecord.setProjectId(task.getProjectId());
                    warehousingSupplierInRecord.setGoodsId(task.getGoodsId());

                    warehousingSupplierInRecord.setSupplierId(entity.getSupplierId());

                    warehousingSupplierInRecord.setCreateTime(new Date());
                    warehousingSupplierInRecord.setModifiedTime(new Date());
                    warehousingSupplierInRecord.setSort(1);
                    warehousingSupplierInRecord.setDeleteStatus(DeleteStatusEnum.normal);

                    warehousingSupplierInRecordMapper.insert(warehousingSupplierInRecord);

                } else if (5 == entity.getStatus().intValue()) { // 驳回
                    Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                    Long rejectNumber = goods.getRejectNumber();
                    if (null == rejectNumber) {
                        goods.setRejectNumber(1L);
                    } else {
                        goods.setRejectNumber(rejectNumber + 1);
                    }
                    goods.setModifiedTime(new Date());
                    goodsMapper.updateByPrimaryKey(goods);
                }
            }
        } else {
            //  无权操作
            throw new UnauthorizedException();
        }
    }

    private void updateStatus( ProjectTaskVO entity) {

        ProjectTask projectTask = new ProjectTask();
        BeanUtils.copyProperties(entity, projectTask);
        projectTask.setModifiedTime(new Date());

        projectTask.setProjectId(entity.getProjectId());

        if (null != entity.getProduct()) {
            projectTask.setProductId(entity.getProduct().getId());
        } else {
            projectTask.setProductId(0L);
        }

        if (null != entity.getSupplier()) {
            projectTask.setSupplierId(entity.getSupplier().getId());
            projectTask.setGoodsId(entity.getSupplier().getGoodsId());
        } else {
            projectTask.setSupplierId(0L);
            projectTask.setGoodsId(0L);
        }

        if (null != entity.getWarehousing()) {
            projectTask.setWarehousingId(entity.getWarehousing().getId());
        } else {
            projectTask.setWarehousingId(0L);
        }

        projectTaskMapper.updateByPrimaryKey(projectTask);

    }

    /**
     * 更新项目
     *
     * @param taskid
     * @param entity
     */
    @Override
    public void editTask(Long taskid, ProjectTaskVO entity) {

        Long projectId = entity.getProjectId();

        Project project = projectMapper.selectByPrimaryKey(projectId);

        if (null == project) return;

        Long allocatorId = project.getAllocatorId();

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (activeUser.getUser().getId().longValue() == allocatorId.longValue()) {


            ProjectTask projectTask = new ProjectTask();
            BeanUtils.copyProperties(entity, projectTask);
            projectTask.setModifiedTime(new Date());

            projectTask.setProjectId(entity.getProjectId());

            if (null != entity.getProduct()) {
                projectTask.setProductId(entity.getProduct().getId());
            } else {
                projectTask.setProductId(0L);
            }

            if (null != entity.getSupplier()) {
                projectTask.setGoodsId(entity.getSupplier().getId());
            } else {
                projectTask.setGoodsId(0L);
            }

            if (null != entity.getWarehousing()) {
                projectTask.setWarehousingId(entity.getWarehousing().getId());
            } else {
                projectTask.setWarehousingId(0L);
            }

            projectTaskMapper.updateByPrimaryKey(projectTask);
        } else {
            //  无权操作
            throw new UnauthorizedException();

        }
    }


//
//    /**
//     * 删除商品
//     * @param id
//     */
//    @Override
//    public void delete(Long id) throws BusinessException {
////        Product t = new Product();
////        t.setId(id);
////        Product product = productMapper.selectByPrimaryKey(t);
////        //只有物资处于回收站,或者待审核的情况下可删除
////        if(product.getStatus()!=1&&product.getStatus()!=2){
////            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
////        }else {
////            productMapper.deleteByPrimaryKey(id);
////        }
//    }
//
//    /**
//     * 物资库存列表
//     * @param pageNum
//     * @param pageSize
//     * @param productVO
//     * @return
//     */
//    @Override
//    public PageVO<ProductStockVO> findProductStocks(Integer pageNum, Integer pageSize, ProjectVO productVO) {
////        PageHelper.startPage(pageNum, pageSize);
////        List<ProductStockVO> productStockVOList=productStockMapper.findProductStocks(productVO);
////        PageInfo<ProductStockVO> info = new PageInfo<>(productStockVOList);
////        return new PageVO<>(info.getTotal(), productStockVOList);
//
//        return null;
//    }
//
//    /**
//     * 所有库存信息
//     * @return
//     */
//    @Override
//    public List<ProductStockVO> findAllStocks(Integer pageNum, Integer pageSize, ProjectVO productVO) {
////        PageHelper.startPage(pageNum, pageSize);
////        return productStockMapper.findAllStocks(productVO);
//
//        return null;
//    }
//
//    /**
//     * 移入回收站
//     * @param id
//     */
//    @Override
//    public void remove(Long id) throws BusinessException {
////        Product t = new Product();
////        t.setId(id);
////        Product product = productMapper.selectByPrimaryKey(t);
////        if(product.getStatus()!=0){
////            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
////        }else {
////            t.setStatus(1);
////            productMapper.updateByPrimaryKeySelective(t);
////        }
//    }
//
//    /**
//     * 从回收站恢复数据
//     * @param id
//     */
//    @Override
//    public void back(Long id) throws BusinessException {
////        Product t = new Product();
////        t.setId(id);
////        Product product = productMapper.selectByPrimaryKey(t);
////        if(product.getStatus()!=1){
////            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
////        }else {
////            t.setStatus(0);
////            productMapper.updateByPrimaryKeySelective(t);
////        }
//    }
//
//    /**
//     * 物资审核
//     * @param id
//     */
//    @Override
//    public void publish(Long id) throws BusinessException {
////        Product t = new Product();
////        t.setId(id);
////        Product product = productMapper.selectByPrimaryKey(t);
////        if(product.getStatus()!=2){
////            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
////        }else {
////            t.setStatus(0);
////            productMapper.updateByPrimaryKeySelective(t);
////        }
//    }


}
