package com.coderman.business.converter;

import com.coderman.business.mapper.ConsumerMapper;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.common.model.business.Consumer;
import com.coderman.common.model.business.Goods;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.GoodsInfoVO;
import com.coderman.common.vo.business.GoodsVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.system.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:22
 * @Version 1.0
 **/
@Component
public class GoodsConverter {


    @Autowired
    private ProductMapper productMapper;


    @Autowired
    private UserMapper userMapper;
    /**
     * 转VOList
     * @param entityList
     * @return
     */
    public    List<GoodsVO> converterToVOList(List<Goods> entityList) {
        List<GoodsVO> productVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(entityList)){
            for (Goods entity : entityList) {
                GoodsVO goodsVO = converterToVO(entity);
                productVOS.add(goodsVO);
            }
        }
        return productVOS;
    }

    /**
     * 转VO
     * @param goods
     * @return
     */
    public  GoodsVO converterToVO(Goods goods) {
        Product product = productMapper.selectByPrimaryKey(goods.getProductId());
        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods,goodsVO);

        goodsVO.setPNum(product.getPNum());
        goodsVO.setName(product.getName());
        goodsVO.setModel(product.getModel());
        goodsVO.setUnit(product.getUnit());
        goodsVO.setOneCategoryId(product.getOneCategoryId());
        goodsVO.setTwoCategoryId(product.getTwoCategoryId());
        goodsVO.setThreeCategoryId(product.getThreeCategoryId());
        goodsVO.setImageUrl(product.getImageUrl());
        goodsVO.setProposedPrice(product.getProposedPrice());

        return goodsVO;
    }


    /**
     * 转VOList
     * @param entityList
     * @return
     */
    public   List<GoodsInfoVO> converterToInfoVOList(List<Goods> entityList) {
        List<GoodsInfoVO> goodsInfoVOList = new ArrayList<>();

        if(!CollectionUtils.isEmpty(entityList)){
            for (Goods entity : entityList) {
                GoodsInfoVO goodsInfoVO = converterToInfoVO(entity);
                goodsInfoVOList.add(goodsInfoVO);
            }
        }
        return goodsInfoVOList;
    }

    /**
     * 转VO
     * @param goods
     * @return
     */
    public  GoodsInfoVO converterToInfoVO(Goods goods) {
        if (null == goods) return null;


        Product product = productMapper.selectByPrimaryKey(goods.getProductId());
        GoodsInfoVO goodsInfoVO = new GoodsInfoVO();
        BeanUtils.copyProperties(goods,goodsInfoVO);

        goodsInfoVO.setPNum(product.getPNum());
        goodsInfoVO.setName(product.getName());
        goodsInfoVO.setModel(product.getModel());
        goodsInfoVO.setUnit(product.getUnit());
        goodsInfoVO.setOneCategoryId(product.getOneCategoryId());
        goodsInfoVO.setTwoCategoryId(product.getTwoCategoryId());
        goodsInfoVO.setThreeCategoryId(product.getThreeCategoryId());
        goodsInfoVO.setImageUrl(product.getImageUrl());
        goodsInfoVO.setProposedPrice(product.getProposedPrice());
        goodsInfoVO.setProductRemark(product.getRemark());



        User userMerchant = userMapper.selectByPrimaryKey(goods.getUserId());
        goodsInfoVO.setMerchantUserName(userMerchant.getUsername());
        goodsInfoVO.setMerchantNickName(userMerchant.getNickname());
        goodsInfoVO.setMerchantPhoneNumber(userMerchant.getPhoneNumber());

        goodsInfoVO.setMerchantProvince(userMerchant.getProvince());
        goodsInfoVO.setMerchantProvinceValue(userMerchant.getProvinceValue());

        goodsInfoVO.setMerchantCity(userMerchant.getCity());
        goodsInfoVO.setMerchantCityValue(userMerchant.getCityValue());

        goodsInfoVO.setMerchantOrigin(userMerchant.getOrigin());
        goodsInfoVO.setMerchantOriginValue(userMerchant.getOriginValue());

        goodsInfoVO.setMerchantAddress(userMerchant.getAddress());

        return goodsInfoVO;
    }
}
