package com.coderman.business.converter;

import com.coderman.business.mapper.GoodsMapper;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProjectMapper;
import com.coderman.common.model.business.Goods;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.Supplier;
import com.coderman.common.model.business.WarehousingSupplier;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.business.SupplierSelectVO;
import com.coderman.common.vo.business.SupplierVO;
import com.coderman.common.vo.business.WarehousingSupplierVO;
import com.coderman.common.vo.system.UserVO;
import com.coderman.system.converter.UserConverter;
import com.coderman.system.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 20:27
 * @Version 1.0
 **/
@Component
public class WarehousingSupplierConverter {


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
     * @param warehousingSupplierList
     * @return
     */
    public List<WarehousingSupplierVO> converterToVOList(List<WarehousingSupplier> warehousingSupplierList) {

        List<WarehousingSupplierVO> warehousingSupplierVOList=new ArrayList<>();
        if(!CollectionUtils.isEmpty(warehousingSupplierList)){
            for (WarehousingSupplier warehousingSupplier : warehousingSupplierList) {
                WarehousingSupplierVO supplierVO = converterToVO(warehousingSupplier);
                warehousingSupplierVOList.add(supplierVO);
            }
        }
        return warehousingSupplierVOList;
    }


    /***
     * 转VO
     * @param warehousingSupplier
     * @return
     */
    public  WarehousingSupplierVO converterToVO(WarehousingSupplier warehousingSupplier) {
        WarehousingSupplierVO warehousingSupplierVO = new WarehousingSupplierVO();
        BeanUtils.copyProperties(warehousingSupplier,warehousingSupplierVO);


        Product t = new Product();
        t.setId(warehousingSupplier.getProductId());
        Product product = productMapper.selectByPrimaryKey(t);


        warehousingSupplierVO.setProduct(productConverter.converterToProductVO(product));
        warehousingSupplierVO.setNumber(warehousingSupplier.getNumber());


        Project project = projectMapper.selectByPrimaryKey(warehousingSupplier.getProjectId());
        warehousingSupplierVO.setProjectName(project.getProjectName());
        warehousingSupplierVO.setProjectId(project.getId());

        User warehousing = userMapper.selectByPrimaryKey(warehousingSupplier.getWarehousingId());
        warehousingSupplierVO.setWarehousing(userConverter.converterToUserVO(warehousing));

        return warehousingSupplierVO;
    }


//    /**
//     * 转voList
//     * @param suppliers
//     * @return
//     */
//    public  List<SupplierSelectVO> converterToSelectList(List<User> suppliers, Long productId) {
//        List<SupplierSelectVO> supplierVOS=new ArrayList<>();
//
//        if(!CollectionUtils.isEmpty(suppliers)){
//            for (User user : suppliers) {
//                SupplierSelectVO supplierVO = converterToSupplierSelectVO(user,productId);
//                supplierVOS.add(supplierVO);
//            }
//        }
//        return supplierVOS;
//    }


    /***
     * 转VO
     * @param user
     * @param productId
     * @return
     */
//    public  SupplierSelectVO converterToSupplierSelectVO(User user,Long productId) {
//        SupplierSelectVO supplierVO = new SupplierSelectVO();
//        BeanUtils.copyProperties(user,supplierVO);
//
//
//        Product t = new Product();
//        t.setId(productId);
//        Product product = productMapper.selectByPrimaryKey(t);
//
//        supplierVO.setProductName(product.getName());
//        supplierVO.setModel(product.getModel());
//        supplierVO.setUnit(product.getUnit());
//        supplierVO.setProposedPrice(product.getProposedPrice());
//        supplierVO.setImageUrl(product.getImageUrl());
//
//
//        Example o = new Example(Goods.class);
//        Example.Criteria criteria = o.createCriteria();
//        criteria.andEqualTo("productId", productId);
//        criteria.andEqualTo("userId", user.getId());
//
////        criteria.andEqualTo("status", 2);
//        o.setOrderByClause("sort asc");
//
//
//
//        List<Goods> goodsList = goodsMapper.selectByExample(o);
//        if ( null != goodsList && goodsList.size() > 0 ){
//            Goods goods =  goodsList.get(0);
//
//            supplierVO.setReceiveNumber(goods.getReceiveNumber());
//            if (null == supplierVO.getReceiveNumber()) supplierVO.setReceiveNumber(0L);
//
//            supplierVO.setRejectNumber(goods.getRejectNumber());
//            if (null == goods.getRejectNumber()) supplierVO.setRejectNumber(0L);
//
//            supplierVO.setGoodsId(goods.getId());
//
//        } else {
//
//            Goods goods = new Goods();
//            goods.setCreateTime(new Date());
//            goods.setModifiedTime(new Date());
//            goods.setStatus(3);
//            goods.setUserId(supplierVO.getId());
//            goods.setProductId(product.getId());
//
//            goodsMapper.insert(goods);
//
//            supplierVO.setReceiveNumber(0L);
//            supplierVO.setRejectNumber(0L);
//            supplierVO.setGoodsId(goods.getId());
////
//            System.out.println("id: "+ goods.getId());
//
//        }
//
//
//        return supplierVO;
//    }







}
