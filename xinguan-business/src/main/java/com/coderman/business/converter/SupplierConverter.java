package com.coderman.business.converter;

import com.coderman.business.mapper.GoodsMapper;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.common.model.business.Goods;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.Supplier;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.GoodsInfoVO;
import com.coderman.common.vo.business.SupplierSelectVO;
import com.coderman.common.vo.business.SupplierVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/16 20:27
 * @Version 1.0
 **/
@Component
public class SupplierConverter {



    @Autowired
    private ProductMapper productMapper;





    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 转voList
     * @param suppliers
     * @return
     */
    public  List<SupplierSelectVO> converterToSelectList(List<User> suppliers, Long productId) {
        List<SupplierSelectVO> supplierVOS=new ArrayList<>();

        if(!CollectionUtils.isEmpty(suppliers)){
            for (User user : suppliers) {
                SupplierSelectVO supplierVO = converterToSupplierSelectVO(user,productId);
                supplierVOS.add(supplierVO);
            }
        }
        return supplierVOS;
    }


    /***
     * 转VO
     * @param user
     * @param productId
     * @return
     */
    public  SupplierSelectVO converterToSupplierSelectVO(User user,Long productId) {
        SupplierSelectVO supplierVO = new SupplierSelectVO();
        BeanUtils.copyProperties(user,supplierVO);


        Product t = new Product();
        t.setId(productId);
        Product product = productMapper.selectByPrimaryKey(t);

        supplierVO.setProductName(product.getName());
        supplierVO.setModel(product.getModel());
        supplierVO.setUnit(product.getUnit());
        supplierVO.setProposedPrice(product.getProposedPrice());
        supplierVO.setImageUrl(product.getImageUrl());


        Example o = new Example(Goods.class);
        Example.Criteria criteria = o.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("userId", user.getId());

//        criteria.andEqualTo("status", 2);
        o.setOrderByClause("sort asc");



        List<Goods> goodsList = goodsMapper.selectByExample(o);
        if ( null != goodsList && goodsList.size() > 0 ){
            Goods goods =  goodsList.get(0);

            supplierVO.setReceiveNumber(goods.getReceiveNumber());
            if (null == supplierVO.getReceiveNumber()) supplierVO.setReceiveNumber(0L);

            supplierVO.setRejectNumber(goods.getRejectNumber());
            if (null == goods.getRejectNumber()) supplierVO.setRejectNumber(0L);

            supplierVO.setGoodsId(goods.getId());

        } else {

            Goods goods = new Goods();
            goods.setCreateTime(new Date());
            goods.setModifiedTime(new Date());
            goods.setStatus(3);
            goods.setUserId(supplierVO.getId());
            goods.setProductId(product.getId());

            goodsMapper.insert(goods);

            supplierVO.setReceiveNumber(0L);
            supplierVO.setRejectNumber(0L);
            supplierVO.setGoodsId(goods.getId());
//
            System.out.println("id: "+ goods.getId());

        }


        return supplierVO;
    }



    /**
     * 转voList
     * @param suppliers
     * @return
     */
    public static List<SupplierVO> converterToVOList(List<Supplier> suppliers) {
        List<SupplierVO> supplierVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(suppliers)){
            for (Supplier supplier : suppliers) {
                SupplierVO supplierVO = converterToSupplierVO(supplier);
                supplierVOS.add(supplierVO);
            }
        }
        return supplierVOS;
    }


    /***
     * 转VO
     * @param supplier
     * @return
     */
    public static SupplierVO converterToSupplierVO(Supplier supplier) {
        SupplierVO supplierVO = new SupplierVO();
        BeanUtils.copyProperties(supplier,supplierVO);
        return supplierVO;
    }
}
