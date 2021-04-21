package com.coderman.business.service.imp;

import com.coderman.business.converter.WarehousingSupplierConverter;
import com.coderman.business.converter.WarehousingSupplierInRecordConverter;
import com.coderman.business.mapper.WarehousingSupplierInRecordMapper;
import com.coderman.business.mapper.WarehousingSupplierMapper;
import com.coderman.business.service.WarehousingSupplierInRecordService;
import com.coderman.business.service.WarehousingSupplierService;
import com.coderman.common.enums.system.RoleEnum;
import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.model.business.WarehousingSupplierInRecord;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;
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
public class WarehousingSupplierInRecordServiceImpl implements WarehousingSupplierInRecordService {


    @Autowired
    private WarehousingSupplierInRecordMapper warehousingSupplierInRecordMapper;



    @Autowired
    private WarehousingSupplierInRecordConverter warehousingSupplierInRecordConverter;


    /**
     * 供应商列表
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @Override
    public PageVO<WarehousingSupplierInRecordVO> allList(Integer pageNum, Integer pageSize, WarehousingSupplierReq entity) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(WarehousingSupplierInRecord.class);
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


        List<WarehousingSupplierInRecord> warehousingSupplierList = warehousingSupplierInRecordMapper.selectByExample(o);
        List<WarehousingSupplierInRecordVO> warehousingSupplierVO = warehousingSupplierInRecordConverter.converterToVOList(warehousingSupplierList);

        PageInfo<WarehousingSupplierInRecord> info = new PageInfo<>(warehousingSupplierList);
        return new PageVO<>(info.getTotal(), warehousingSupplierVO);
    }


    /**
     * 供应商列表
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     */
    @Override
    public PageVO<WarehousingSupplierInRecordVO> list(Integer pageNum, Integer pageSize, WarehousingSupplierReq entity) {
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


        List<WarehousingSupplierInRecord> warehousingSupplierList = warehousingSupplierInRecordMapper.selectByExample(o);
        List<WarehousingSupplierInRecordVO> warehousingSupplierVO = warehousingSupplierInRecordConverter.converterToVOList(warehousingSupplierList);

        PageInfo<WarehousingSupplierInRecord> info = new PageInfo<>(warehousingSupplierList);
        return new PageVO<>(info.getTotal(), warehousingSupplierVO);

    }


}
