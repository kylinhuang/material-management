package com.coderman.business.service.imp;

import com.coderman.business.converter.MerchantConverter;
import com.coderman.business.converter.ProjectConverter;
import com.coderman.business.mapper.MerchantInfoMapper;
import com.coderman.business.service.MerchantInfoService;
import com.coderman.common.enums.system.RoleEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.merchant.MerchantInfo;
import com.coderman.common.model.project.Project;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.business.MerchantInfoVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProjectVO;
import com.coderman.common.vo.system.PageVO;
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

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:19
 * @Version 1.0
 **/
@Service
@Transactional
public class MerchantInfoServiceImpl implements MerchantInfoService {


    @Autowired
    private MerchantInfoMapper merchantInfoMapper;


    @Autowired
    private UserMapper userMapper;



    @Autowired
    private MerchantConverter merchantConverter;



    /**
     * 项目列表
     * @param pageNum
     * @param pageSize
     * @param merchantInfoVO
     * @return
     */
    @Override
    public PageVO<MerchantInfoVO> findList(Integer pageNum, Integer pageSize, MerchantInfoVO merchantInfoVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<Project> projects;
        Example o = new Example(MerchantInfo.class);
        Example.Criteria criteria = o.createCriteria();
        if (merchantInfoVO.getStatus() != null &&  -1 != merchantInfoVO.getStatus().intValue()) {
            criteria.andEqualTo("status", merchantInfoVO.getStatus());
        }

        o.setOrderByClause("sort asc");
//        if (projectVO.getProjectName() != null && !"".equals(projectVO.getProjectName())) {
//            criteria.andLike("name", "%" + projectVO.getProjectName() + "%");
//        }

        List<MerchantInfo> merchantInfoList = merchantInfoMapper.selectByExample(o);

        List<MerchantInfoVO> list = merchantConverter.converterList(merchantInfoList);
        PageInfo<MerchantInfo> info = new PageInfo<MerchantInfo>(merchantInfoList);
        return new PageVO<>(info.getTotal(), list);

    }




    /**
     * 项目信息
     * @return
     */
    @Override
    public MerchantInfoVO findMerchantInfo() {

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        Long userId = activeUser.getUser().getId();
        Example o = new Example(MerchantInfo.class);
        Example.Criteria criteria = o.createCriteria();

        criteria.andEqualTo("userId", userId);

        o.setOrderByClause("sort asc");


        List<MerchantInfo> merchantInfoList = merchantInfoMapper.selectByExample(o);
        if (null != merchantInfoList && merchantInfoList.size()>0 ){
            List<MerchantInfoVO> list =  merchantConverter.converterList(merchantInfoList);
            return list.get(0) ;
        }
        return new MerchantInfoVO();
    }






    /**
     * 添加商户信息
     * @param merchantInfoVO
     */
    @Override
    public void add(MerchantInfoVO merchantInfoVO) {
        MerchantInfo merchantInfo = new MerchantInfo();
        BeanUtils.copyProperties(merchantInfoVO,merchantInfo);
        merchantInfo.setCreateTime(new Date());
        merchantInfo.setModifiedTime(new Date());
//        @NotNull(message = "分类不能为空") Long[] categoryKeys = projectVO.getCategoryKeys();
//        if(categoryKeys.length>=1) product.setOneCategoryId(categoryKeys[0]);
//
//        if(categoryKeys.length>=2) product.setOneCategoryId(categoryKeys[1]);
//        if(categoryKeys.length>=3) product.setOneCategoryId(categoryKeys[2]);
//
////        product.setStatus(2);//未审核clearValidate
        merchantInfo.setStatus(0);
//
        merchantInfoMapper.insert(merchantInfo);
    }

    /**
     * 编辑商品
     * @param id
     * @return
     */
    @Override
    public MerchantInfoVO edit(Long id) {

        merchantInfoMapper.selectByPrimaryKey(id);
//        Project project = projectMapper.selectByPrimaryKey(id);
//        return projectConverter.converterToProjectVO(project);
        return null;
    }



    /**
     * 更新项目
     * @param id
     * @param merchantInfoVO
     */
    @Override
    public void update(Long id, MerchantInfoVO merchantInfoVO) {
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(id);
        if (null == merchantInfo) {
            add(merchantInfoVO);
        } else {
            MerchantInfo merchantInfoEdit = new MerchantInfo();

            BeanUtils.copyProperties(merchantInfoVO, merchantInfoEdit);
            merchantInfoEdit.setModifiedTime(new Date());
            merchantInfoEdit.setStatus(0);// 创建
            merchantInfoMapper.updateByPrimaryKey(merchantInfoEdit);

        }
    }


    /**
     * 更新项目
     * @param id
     * @param merchantInfoVO
     */
    @Override
    public void updateStatus(Long id, MerchantInfoVO merchantInfoVO) {
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(id);
        if (null != merchantInfo){
            MerchantInfo merchantInfoEdit = new MerchantInfo();

            BeanUtils.copyProperties(merchantInfoVO, merchantInfoEdit);
            merchantInfoEdit.setModifiedTime(new Date());
            merchantInfoMapper.updateByPrimaryKey(merchantInfoEdit);
        }

    }


    /**
     * 删除商品
     * @param id
     */
    @Override
    public void delete(Long id) throws BusinessException {
//        Product t = new Product();
//        t.setId(id);
//        Product product = productMapper.selectByPrimaryKey(t);
//        //只有物资处于回收站,或者待审核的情况下可删除
//        if(product.getStatus()!=1&&product.getStatus()!=2){
//            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
//        }else {
//            productMapper.deleteByPrimaryKey(id);
//        }
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
