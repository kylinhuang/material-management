package com.coderman.business.service.imp;

import com.coderman.business.converter.SupplierConverter;
import com.coderman.business.converter.WarehousingSupplierConverter;
import com.coderman.business.mapper.SupplierMapper;
import com.coderman.business.mapper.WarehousingSupplierMapper;
import com.coderman.business.mapper.WarehousingSupplierOutRecordMapper;
import com.coderman.business.service.SupplierService;
import com.coderman.business.service.WarehousingSupplierService;
import com.coderman.common.enums.system.ProductStatusEnum;
import com.coderman.common.enums.system.RoleEnum;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.business.Supplier;
import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.model.business.WarehousingSupplierOutRecord;
import com.coderman.common.model.system.User;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;
import com.coderman.system.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:19
 * @Version 1.0
 **/
@Service
public class WarehousingSupplierServiceImpl implements WarehousingSupplierService {


    @Autowired
    private WarehousingSupplierMapper warehousingSupplierMapper;




    @Autowired
    private WarehousingSupplierOutRecordMapper warehousingSupplierOutRecordMapper;


    @Autowired
    private WarehousingSupplierConverter warehousingSupplierConverter;


    /**
     * 供应商列表
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @Override
    public PageVO<WarehousingSupplierVO> allList(Integer pageNum, Integer pageSize, WarehousingSupplierReq entity) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(WarehousingSupplier.class);
        Example.Criteria criteria = o.createCriteria();
        o.setOrderByClause("sort asc");


        if (null != entity.getWarehousingId()){
            criteria.andEqualTo("warehousingId",entity.getWarehousingId());
        }

        if (null != entity.getProjectId()){
            criteria.andEqualTo("projectId",entity.getProjectId());
        }



//        private String productName;



//        if (entity.getName() != null && !"".equals(entity.getName())) {
//            criteria.andLike("name", "%" + entity.getName() + "%");
//        }
//        if (entity.getContact() != null && !"".equals(supplierVO.getContact())) {
//            criteria.andLike("contact", "%" + supplierVO.getContact() + "%");
//        }
//        if (supplierVO.getAddress() != null && !"".equals(supplierVO.getAddress())) {
//            criteria.andLike("address", "%" + supplierVO.getAddress() + "%");
//        }


        List<WarehousingSupplier> warehousingSupplierList = warehousingSupplierMapper.selectByExample(o);
        List<WarehousingSupplierVO> warehousingSupplierVO= warehousingSupplierConverter.converterToVOList(warehousingSupplierList);

        PageInfo<WarehousingSupplier> info = new PageInfo<>(warehousingSupplierList);
        return new PageVO<>(info.getTotal(), warehousingSupplierVO);
//        return null;
    }


    /**
     * 供应商列表
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @Override
    public PageVO<WarehousingSupplierVO> list(Integer pageNum, Integer pageSize, WarehousingSupplierReq entity) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(WarehousingSupplier.class);
        Example.Criteria criteria = o.createCriteria();
        o.setOrderByClause("sort asc");


        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (activeUser.getUser().getRoleId()== RoleEnum.WarehouseManagement.getId()){
            criteria.andEqualTo("warehousingId", activeUser.getUser().getId());
        }

        if (null != entity.getProjectId()){
            criteria.andEqualTo("projectId",entity.getProjectId());
        }



//        private String productName;



//        if (entity.getName() != null && !"".equals(entity.getName())) {
//            criteria.andLike("name", "%" + entity.getName() + "%");
//        }
//        if (entity.getContact() != null && !"".equals(supplierVO.getContact())) {
//            criteria.andLike("contact", "%" + supplierVO.getContact() + "%");
//        }
//        if (supplierVO.getAddress() != null && !"".equals(supplierVO.getAddress())) {
//            criteria.andLike("address", "%" + supplierVO.getAddress() + "%");
//        }


        List<WarehousingSupplier> warehousingSupplierList = warehousingSupplierMapper.selectByExample(o);
        List<WarehousingSupplierVO> warehousingSupplierVO= warehousingSupplierConverter.converterToVOList(warehousingSupplierList);

        PageInfo<WarehousingSupplier> info = new PageInfo<>(warehousingSupplierList);
        return new PageVO<>(info.getTotal(), warehousingSupplierVO);
//        return null;
    }

    /**
     * 供应商列表
     * @param pageNum
     * @param pageSize
     * @param supplierVO
     * @return
     */
    @Override
    public PageVO<SupplierVO> findSupplierList(Integer pageNum, Integer pageSize, SupplierVO supplierVO) {
//        PageHelper.startPage(pageNum, pageSize);
//        Example o = new Example(Supplier.class);
//        Example.Criteria criteria = o.createCriteria();
//        o.setOrderByClause("sort asc");
//        if (supplierVO.getName() != null && !"".equals(supplierVO.getName())) {
//            criteria.andLike("name", "%" + supplierVO.getName() + "%");
//        }
//        if (supplierVO.getContact() != null && !"".equals(supplierVO.getContact())) {
//            criteria.andLike("contact", "%" + supplierVO.getContact() + "%");
//        }
//        if (supplierVO.getAddress() != null && !"".equals(supplierVO.getAddress())) {
//            criteria.andLike("address", "%" + supplierVO.getAddress() + "%");
//        }
//        List<Supplier> suppliers = warehousingSupplierMapper.selectByExample(o);
//        List<SupplierVO> categoryVOS= SupplierConverter.converterToVOList(suppliers);
//        PageInfo<Supplier> info = new PageInfo<>(suppliers);
//        return new PageVO<>(info.getTotal(), categoryVOS);
        return null;
    }

    /**
     * 供应商列表
     * @param productId
     * @return
     */
    @Override
    public PageVO<SupplierSelectVO> selectList( Long productId) {

//        Example o = new Example(User.class);
//
//        Example.Criteria criteria = o.createCriteria();
//        criteria.andEqualTo("roleId",RoleEnum.supplier.getId());
//
//        List<User> userList = userMapper.selectByExample(o);
//
//        List<SupplierSelectVO> userVOS = supplierConverter.converterToSelectList(userList,productId);
//
//        PageInfo<User> info=new PageInfo<>(userList);
//        return new PageVO<>(info.getTotal(),userVOS);
        return null;

    }


    /**
     * 添加供应商
     * @param warehousingSupplierVO
     */
    @Override
    public WarehousingSupplier add(WarehousingSupplierVO warehousingSupplierVO) {
        WarehousingSupplier warehousingSupplier = new WarehousingSupplier();
        BeanUtils.copyProperties(warehousingSupplierVO,warehousingSupplier);
        warehousingSupplier.setCreateTime(new Date());
        warehousingSupplier.setModifiedTime(new Date());

        warehousingSupplierMapper.insert(warehousingSupplier);
        return warehousingSupplier;
    }

    /**
     * 编辑供应商
     * @param id
     * @return
     */
    @Override
    public SupplierVO edit(Long id) {
//        Supplier supplier = supplierMapper.selectByPrimaryKey(id);
//        return SupplierConverter.converterToSupplierVO(supplier);
        return null;
    }

    /**
     * 更新供应商
     * @param id
     * @param bean
     */
    @Override
    public void receive(Long id, WarehousingSupplierOutRecordVO bean) throws BusinessException {

        WarehousingSupplier warehousingSupplier = warehousingSupplierMapper.selectByPrimaryKey(id);
        if (null != warehousingSupplier) {
            Long number = warehousingSupplier.getNumber() - bean.getNumber();

            if (number < 0){
                throw new BusinessException(BusinessCodeEnum.PRODUCT_OUT_STOCK_NUMBER_ERROR);

            } else if (number > 0) {
                warehousingSupplier.setNumber(number);
                warehousingSupplier.setModifiedTime(new Date());
                warehousingSupplierMapper.updateByPrimaryKeySelective(warehousingSupplier);

                // 更新领取记录
                updateOutRecord(bean);

            } else if (number == 0) {
                warehousingSupplierMapper.deleteByPrimaryKey(id);
                // 更新领取记录
                updateOutRecord(bean);

            }

        }

    }

    private void updateOutRecord(WarehousingSupplierOutRecordVO bean) {
        WarehousingSupplierOutRecord warehousingSupplierOutRecord =  new WarehousingSupplierOutRecord();
        BeanUtils.copyProperties(bean,warehousingSupplierOutRecord);


//        warehousingSupplierOutRecord.setNumber(bean.getNumber());


        warehousingSupplierOutRecord.setCreateTime(new Date());
        warehousingSupplierOutRecord.setModifiedTime(new Date());

        warehousingSupplierOutRecordMapper.insert(warehousingSupplierOutRecord);
    }


    /**
     * 删除供应商
     * @param id
     */
    @Override
    public void delete(Long id) {
//        supplierMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<SupplierVO> findAll() {
//        List<Supplier> suppliers = supplierMapper.selectAll();
//        return SupplierConverter.converterToVOList(suppliers);
        return null;
    }

}
