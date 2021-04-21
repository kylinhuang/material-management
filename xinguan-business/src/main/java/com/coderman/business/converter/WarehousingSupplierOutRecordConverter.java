package com.coderman.business.converter;

import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProjectMapper;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.WarehousingSupplierInRecord;
import com.coderman.common.model.business.WarehousingSupplierOutRecord;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.WarehousingSupplierInRecordVO;
import com.coderman.common.vo.business.WarehousingSupplierOutRecordVO;
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
public class WarehousingSupplierOutRecordConverter {


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
    public List<WarehousingSupplierOutRecordVO> converterToVOList(List<WarehousingSupplierOutRecord> warehousingSupplierInRecordList) {

        List<WarehousingSupplierOutRecordVO> warehousingSupplierOutRecordVOList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(warehousingSupplierInRecordList)){
            for (WarehousingSupplierOutRecord warehousingSupplier : warehousingSupplierInRecordList) {
                WarehousingSupplierOutRecordVO supplierVO = converterToVO(warehousingSupplier);
                warehousingSupplierOutRecordVOList.add(supplierVO);
            }
        }

        return warehousingSupplierOutRecordVOList;
    }


    /***
     * 转VO
     * @param warehousingSupplierOutRecord
     * @return
     */
    public  WarehousingSupplierOutRecordVO converterToVO(WarehousingSupplierOutRecord warehousingSupplierOutRecord) {
        WarehousingSupplierOutRecordVO warehousingSupplierInRecordVO = new WarehousingSupplierOutRecordVO();
        BeanUtils.copyProperties(warehousingSupplierOutRecord,warehousingSupplierInRecordVO);

        Product t = new Product();
        t.setId(warehousingSupplierOutRecord.getProductId());
        Product product = productMapper.selectByPrimaryKey(t);


        warehousingSupplierInRecordVO.setProduct(productConverter.converterToProductVO(product));
        warehousingSupplierInRecordVO.setNumber(warehousingSupplierOutRecord.getNumber());


        Project project = projectMapper.selectByPrimaryKey(warehousingSupplierOutRecord.getProjectId());
        warehousingSupplierInRecordVO.setProjectName(project.getProjectName());
        warehousingSupplierInRecordVO.setProjectId(project.getId());

        User warehousing = userMapper.selectByPrimaryKey(warehousingSupplierOutRecord.getWarehousingId());
        warehousingSupplierInRecordVO.setWarehousing(userConverter.converterToUserVO(warehousing));


//        User supplier = userMapper.selectByPrimaryKey(warehousingSupplierOutRecord.getSupplierId());
//        warehousingSupplierInRecordVO.setSupplier(userConverter.converterToUserVO(supplier));

        return warehousingSupplierInRecordVO;
    }

}
