package com.coderman.business.converter;

import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProjectMapper;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.model.business.WarehousingSupplierInRecord;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.WarehousingSupplierInRecordVO;
import com.coderman.common.vo.business.WarehousingSupplierVO;
import com.coderman.system.converter.UserConverter;
import com.coderman.system.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 20:27
 * @Version 1.0
 **/
@Component
public class WarehousingSupplierInRecordConverter {


    @Autowired
    private ProductMapper productMapper;



    @Autowired
    private ProductConverter productConverter;


    @Autowired
    private ProjectMapper projectMapper;


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserConverter userConverter;



    /**
     * 转voList
     * @param warehousingSupplierInRecordList
     * @return
     */
    public List<WarehousingSupplierInRecordVO> converterToVOList(List<WarehousingSupplierInRecord> warehousingSupplierInRecordList) {

        List<WarehousingSupplierInRecordVO> WarehousingSupplierInRecordVOList=new ArrayList<>();
        if(!CollectionUtils.isEmpty(warehousingSupplierInRecordList)){
            for (WarehousingSupplierInRecord warehousingSupplier : warehousingSupplierInRecordList) {
                WarehousingSupplierInRecordVO supplierVO = converterToVO(warehousingSupplier);
                WarehousingSupplierInRecordVOList.add(supplierVO);
            }
        }

        return WarehousingSupplierInRecordVOList;
    }


    /***
     * 转VO
     * @param warehousingSupplierInRecord
     * @return
     */
    public  WarehousingSupplierInRecordVO converterToVO(WarehousingSupplierInRecord warehousingSupplierInRecord) {
        WarehousingSupplierInRecordVO warehousingSupplierInRecordVO = new WarehousingSupplierInRecordVO();
        BeanUtils.copyProperties(warehousingSupplierInRecord,warehousingSupplierInRecordVO);

        Product t = new Product();
        t.setId(warehousingSupplierInRecord.getProductId());
        Product product = productMapper.selectByPrimaryKey(t);


        warehousingSupplierInRecordVO.setProduct(productConverter.converterToProductVO(product));
        warehousingSupplierInRecordVO.setNumber(warehousingSupplierInRecord.getNumber());


        Project project = projectMapper.selectByPrimaryKey(warehousingSupplierInRecord.getProjectId());
        warehousingSupplierInRecordVO.setProjectName(project.getProjectName());
        warehousingSupplierInRecordVO.setProjectId(project.getId());

        User warehousing = userMapper.selectByPrimaryKey(warehousingSupplierInRecord.getWarehousingId());
        warehousingSupplierInRecordVO.setWarehousing(userConverter.converterToUserVO(warehousing));


        User supplier = userMapper.selectByPrimaryKey(warehousingSupplierInRecord.getSupplierId());
        warehousingSupplierInRecordVO.setSupplier(userConverter.converterToUserVO(supplier));

        return warehousingSupplierInRecordVO;
    }

}
