package com.coderman.business.service.imp;

import com.coderman.business.converter.WarehousingSupplierInRecordConverter;
import com.coderman.business.converter.WarehousingSupplierOutRecordConverter;
import com.coderman.business.mapper.WarehousingSupplierInRecordMapper;
import com.coderman.business.mapper.WarehousingSupplierOutRecordMapper;
import com.coderman.business.service.WarehousingSupplierInRecordService;
import com.coderman.business.service.WarehousingSupplierOutRecordService;
import com.coderman.common.enums.system.RoleEnum;
import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.model.business.WarehousingSupplierInRecord;
import com.coderman.common.model.business.WarehousingSupplierOutRecord;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 17:19
 * @Version 1.0
 **/
@Service
public class WarehousingSupplierOutRecordServiceImpl implements WarehousingSupplierOutRecordService {


    @Autowired
    private WarehousingSupplierOutRecordMapper warehousingSupplierOutRecordMapper;



    @Autowired
    private WarehousingSupplierOutRecordConverter warehousingSupplierOutRecordConverter;



    /**
     * 供应商列表
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @Override
    public PageVO<WarehousingSupplierOutRecordVO> list(Integer pageNum, Integer pageSize, WarehousingSupplierReq entity) {
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

//        if (entity.getName() != null && !"".equals(entity.getName())) {
//            criteria.andLike("name", "%" + entity.getName() + "%");
//        }
//        if (entity.getContact() != null && !"".equals(supplierVO.getContact())) {
//            criteria.andLike("contact", "%" + supplierVO.getContact() + "%");
//        }
//        if (supplierVO.getAddress() != null && !"".equals(supplierVO.getAddress())) {
//            criteria.andLike("address", "%" + supplierVO.getAddress() + "%");
//        }


        List<WarehousingSupplierOutRecord> warehousingSupplierList = warehousingSupplierOutRecordMapper.selectByExample(o);
        List<WarehousingSupplierOutRecordVO> warehousingSupplierVO = warehousingSupplierOutRecordConverter.converterToVOList(warehousingSupplierList);

        PageInfo<WarehousingSupplierOutRecord> info = new PageInfo<>(warehousingSupplierList);
        return new PageVO<>(info.getTotal(), warehousingSupplierVO);

    }


    @Override
    public PageVO<WarehousingSupplierOutRecordVO> allList(Integer pageNum, Integer pageSize, WarehousingSupplierReq entity) {
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

//        if (entity.getName() != null && !"".equals(entity.getName())) {
//            criteria.andLike("name", "%" + entity.getName() + "%");
//        }
//        if (entity.getContact() != null && !"".equals(supplierVO.getContact())) {
//            criteria.andLike("contact", "%" + supplierVO.getContact() + "%");
//        }
//        if (supplierVO.getAddress() != null && !"".equals(supplierVO.getAddress())) {
//            criteria.andLike("address", "%" + supplierVO.getAddress() + "%");
//        }


        List<WarehousingSupplierOutRecord> warehousingSupplierList = warehousingSupplierOutRecordMapper.selectByExample(o);
        List<WarehousingSupplierOutRecordVO> warehousingSupplierVO = warehousingSupplierOutRecordConverter.converterToVOList(warehousingSupplierList);

        PageInfo<WarehousingSupplierOutRecord> info = new PageInfo<>(warehousingSupplierList);
        return new PageVO<>(info.getTotal(), warehousingSupplierVO);
    }


}
