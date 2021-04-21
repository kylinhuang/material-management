package com.coderman.business.service.imp;

import com.coderman.business.converter.ProjectConverter;
import com.coderman.business.mapper.ProjectMapper;
import com.coderman.business.service.ProjectService;
import com.coderman.common.enums.system.DeleteStatusEnum;
import com.coderman.common.enums.system.RoleEnum;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.system.User;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProjectVO;
import com.coderman.common.vo.system.PageVO;
import com.coderman.system.converter.UserConverter;
import com.coderman.system.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:19
 * @Version 1.0
 **/
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectMapper projectMapper;


    @Autowired
    private UserMapper userMapper;



    @Autowired
    private ProjectConverter projectConverter;

    /**
     * 项目列表
     * @param pageNum
     * @param pageSize
     * @param projectVO
     * @return
     */
    @Override
    public PageVO<ProjectVO> findProjectList(Integer pageNum, Integer pageSize, ProjectVO projectVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<Project> projects;
        Example o = new Example(Project.class);
        Example.Criteria criteria = o.createCriteria();
        if (null != projectVO.getStatus() && -1 != projectVO.getStatus()) {
            criteria.andEqualTo("status", projectVO.getStatus());
        }

        criteria.andEqualTo("deleteStatus", DeleteStatusEnum.normal);

//        o.setOrderByClause("sort asc");
        o.setOrderByClause("create_time desc");
        if (projectVO.getProjectName() != null && !"".equals(projectVO.getProjectName())) {
            criteria.andLike("name", "%" + projectVO.getProjectName() + "%");
        }

        projects = projectMapper.selectByExample(o);
        List<ProjectVO> categoryVOS = projectConverter.converterToVOList(projects);
        PageInfo<Project> info = new PageInfo<Project>(projects);
        return new PageVO<>(info.getTotal(), categoryVOS);

    }




    /**
     * 我的项目列表
     * @param pageNum
     * @param pageSize
     * @param projectVO
     * @return
     */
    @Override
    public PageVO<ProjectVO> findMyProjectList(Integer pageNum, Integer pageSize, ProjectVO projectVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<Project> projects;
        Example o = new Example(Project.class);
        Example.Criteria criteria = o.createCriteria();

        if (null != projectVO.getStatus() && -1 != projectVO.getStatus()) {
            criteria.andEqualTo("status", projectVO.getStatus());
        }

        criteria.andEqualTo("deleteStatus", DeleteStatusEnum.normal);


        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();



        // 查询创建
        if (activeUser.getUser().getRoleId() == RoleEnum.ProjectManage.getId()){
            criteria.andEqualTo("creatorId", userId);
        }


        // 查询管理 分配
        if (activeUser.getUser().getRoleId() == RoleEnum.MaterialManagement.getId()){
            criteria.andEqualTo("allocatorId", userId);

        }



//        if(projectVO.getThreeCategoryId()!=null){
//            criteria.andEqualTo("oneCategoryId",projectVO.getOneCategoryId())
//                    .andEqualTo("twoCategoryId",projectVO.getTwoCategoryId())
//                    .andEqualTo("threeCategoryId",projectVO.getThreeCategoryId());
//            projects = productMapper.selectByExample(o);
//            List<ProjectVO> categoryVOS= ProductConverter.converterToVOList(products);
//            PageInfo<Product> info = new PageInfo<>(products);
//            return new PageVO<>(info.getTotal(), categoryVOS);
//        }
//        if(projectVO.getTwoCategoryId()!=null){
//            criteria.andEqualTo("oneCategoryId",projectVO.getOneCategoryId())
//                    .andEqualTo("twoCategoryId",projectVO.getTwoCategoryId());
//            projects = productMapper.selectByExample(o);
//            List<ProjectVO> categoryVOS=ProductConverter.converterToVOList(products);
//            PageInfo<Product> info = new PageInfo<>(products);
//            return new PageVO<>(info.getTotal(), categoryVOS);
//        }
//        if(projectVO.getOneCategoryId()!=null) {
//            criteria.andEqualTo("oneCategoryId", projectVO.getOneCategoryId());
//            projects = productMapper.selectByExample(o);
//            List<ProjectVO> categoryVOS = ProductConverter.converterToVOList(products);
//            PageInfo<Product> info = new PageInfo<>(products);
//            return new PageVO<>(info.getTotal(), categoryVOS);
//        }

//        o.setOrderByClause("sort asc");
        o.setOrderByClause("create_time desc");
        if (projectVO.getProjectName() != null && !"".equals(projectVO.getProjectName())) {
            criteria.andLike("name", "%" + projectVO.getProjectName() + "%");
        }

        projects = projectMapper.selectByExample(o);
        List<ProjectVO> categoryVOS=projectConverter.converterToVOList(projects);
        PageInfo<Project> info = new PageInfo<Project>(projects);
        return new PageVO<>(info.getTotal(), categoryVOS);

    }



    /**
     * 添加商品
     * @param projectVO
     */
    @Override
    public ProjectVO add(ProjectVO projectVO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectVO,project);
        project.setCreateTime(new Date());
        project.setModifiedTime(new Date());

        project.setProjectNum(UUID.randomUUID().toString().substring(0,32));
        projectMapper.insert(project);

        System.out.println(project.getId());

        return projectConverter.converterToProjectVO(project);

    }

    /**
     * 编辑商品
     * @param id
     * @return
     */
    @Override
    public ProjectVO edit(Long id) {
        Project project = projectMapper.selectByPrimaryKey(id);
        return projectConverter.converterToProjectVO(project);
    }


    /**
     * 编辑商品
     * @param id
     * @return
     */
    @Override
    public ProjectVO info(Long id) {
        Project project = projectMapper.selectByPrimaryKey(id);
        return projectConverter.converterToProjectVO(project);
    }

    /**
     * 更新项目
     * @param id
     * @param projectVO
     */
    @Override
    public void update(Long id, ProjectVO projectVO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectVO,project);
        project.setModifiedTime(new Date());

        projectMapper.updateByPrimaryKey(project);
    }

    /**
     * 删除商品
     * @param id
     */
    @Override
    public void delete(Long id) throws BusinessException {
        Project t = new Project();
        t.setId(id);
        Project project = projectMapper.selectByPrimaryKey(t);

        if(project.getDeleteStatus() == DeleteStatusEnum.delete ){
            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
        }else {
            project.setDeleteStatus(DeleteStatusEnum.delete);
            projectMapper.updateByPrimaryKey(project);
        }
    }

    /**
     * 物资库存列表
     * @param pageNum
     * @param pageSize
     * @param productVO
     * @return
     */
    @Override
    public PageVO<ProductStockVO> findProductStocks(Integer pageNum, Integer pageSize, ProjectVO productVO) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<ProductStockVO> productStockVOList=productStockMapper.findProductStocks(productVO);
//        PageInfo<ProductStockVO> info = new PageInfo<>(productStockVOList);
//        return new PageVO<>(info.getTotal(), productStockVOList);

        return null;
    }

    /**
     * 所有库存信息
     * @return
     */
    @Override
    public List<ProductStockVO> findAllStocks(Integer pageNum, Integer pageSize, ProjectVO productVO) {
//        PageHelper.startPage(pageNum, pageSize);
//        return productStockMapper.findAllStocks(productVO);

        return null;
    }

    /**
     * 移入回收站
     * @param id
     */
    @Override
    public void remove(Long id) throws BusinessException {
//        Product t = new Product();
//        t.setId(id);
//        Product product = productMapper.selectByPrimaryKey(t);
//        if(product.getStatus()!=0){
//            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
//        }else {
//            t.setStatus(1);
//            productMapper.updateByPrimaryKeySelective(t);
//        }
    }

    /**
     * 从回收站恢复数据
     * @param id
     */
    @Override
    public void back(Long id) throws BusinessException {
//        Product t = new Product();
//        t.setId(id);
//        Product product = productMapper.selectByPrimaryKey(t);
//        if(product.getStatus()!=1){
//            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
//        }else {
//            t.setStatus(0);
//            productMapper.updateByPrimaryKeySelective(t);
//        }
    }

    /**
     * 物资审核
     * @param id
     */
    @Override
    public void publish(Long id) throws BusinessException {
//        Product t = new Product();
//        t.setId(id);
//        Product product = productMapper.selectByPrimaryKey(t);
//        if(product.getStatus()!=2){
//            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
//        }else {
//            t.setStatus(0);
//            productMapper.updateByPrimaryKeySelective(t);
//        }
    }



}
