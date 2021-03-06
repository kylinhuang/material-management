package com.coderman.business.service.imp;

import com.coderman.business.converter.GoodsConverter;
import com.coderman.business.converter.ProductConverter;
import com.coderman.business.mapper.GoodsMapper;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProductStockMapper;
import com.coderman.business.service.GoodsService;
import com.coderman.business.service.ProductService;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.business.Goods;
import com.coderman.common.model.business.Product;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.vo.business.GoodsInfoVO;
import com.coderman.common.vo.business.GoodsVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotNull;
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
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;




    @Autowired
    private GoodsConverter goodsConverter;




//    @Autowired
//    private ProductStockMapper productStockMapper;
//


    /**
     * ๅๅๅ่กจ
     * @param pageNum
     * @param pageSize
     * @param goodsVO
     * @return
     */
    @Override
    public PageVO<GoodsVO> findList(Integer pageNum, Integer pageSize, GoodsVO goodsVO) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods;
        Example o = new Example(Goods.class);
        Example.Criteria criteria = o.createCriteria();
        if (goodsVO.getStatus() != null) {

            if (goodsVO.getStatus() != 0){
                criteria.andEqualTo("status", goodsVO.getStatus());
            }
        }


        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        if (null != activeUser.getUser().getId()){
            criteria.andEqualTo("userId", activeUser.getUser().getId());
        }



//        if(goodsVO.getThreeCategoryId()!=null){
//            criteria.andEqualTo("oneCategoryId",productVO.getOneCategoryId())
//                    .andEqualTo("twoCategoryId",productVO.getTwoCategoryId())
//                    .andEqualTo("threeCategoryId",productVO.getThreeCategoryId());
//            products = productMapper.selectByExample(o);
//            List<ProductVO> categoryVOS= ProductConverter.converterToVOList(products);
//            PageInfo<Product> info = new PageInfo<>(products);
//            return new PageVO<>(info.getTotal(), categoryVOS);
//        }
//        if(productVO.getTwoCategoryId()!=null){
//            criteria.andEqualTo("oneCategoryId",productVO.getOneCategoryId())
//                    .andEqualTo("twoCategoryId",productVO.getTwoCategoryId());
//            products = productMapper.selectByExample(o);
//            List<ProductVO> categoryVOS=ProductConverter.converterToVOList(products);
//            PageInfo<Product> info = new PageInfo<>(products);
//            return new PageVO<>(info.getTotal(), categoryVOS);
//        }
//        if(productVO.getOneCategoryId()!=null) {
//            criteria.andEqualTo("oneCategoryId", productVO.getOneCategoryId());
//            products = productMapper.selectByExample(o);
//            List<ProductVO> categoryVOS = ProductConverter.converterToVOList(products);
//            PageInfo<Product> info = new PageInfo<>(products);
//            return new PageVO<>(info.getTotal(), categoryVOS);
//        }
        o.setOrderByClause("sort asc");
        if (goodsVO.getUserId() != null && !"".equals(goodsVO.getUserId())) {
            criteria.andLike("userId", "%" + goodsVO.getUserId() + "%");
        }

        goods = goodsMapper.selectByExample(o);
        List<GoodsVO> categoryVOS =goodsConverter.converterToVOList(goods);
        PageInfo<Goods> info = new PageInfo<>(goods);
        return new PageVO<>(info.getTotal(), categoryVOS);
    }





    /**
     * ๅๅๅ่กจ
     * @param pageNum
     * @param pageSize
     * @param productId
     * @return
     */
    @Override
    public PageVO<GoodsInfoVO> findGoodList(Integer pageNum, Integer pageSize, Long productId) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(Goods.class);
        Example.Criteria criteria = o.createCriteria();

        criteria.andEqualTo("productId", productId);

//        criteria.andEqualTo("status", 3);
        o.setOrderByClause("sort asc");
//        if (goodsVO.getUserId() != null && !"".equals(goodsVO.getUserId())) {
//            criteria.andLike("userId", "%" + goodsVO.getUserId() + "%");
//        }

        List<Goods> goods = goodsMapper.selectByExample(o);
        List<GoodsInfoVO> categoryVOS = goodsConverter.converterToInfoVOList(goods);
        PageInfo<Goods> info = new PageInfo<>(goods);
        return new PageVO<>(info.getTotal(), categoryVOS);
    }





    /**
     * ๅๅๅ่กจ
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageVO<GoodsInfoVO> findReviewList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(Goods.class);
        Example.Criteria criteria = o.createCriteria();

//        criteria.andEqualTo("productId", productId);

        criteria.andEqualTo("status", 2);
        o.setOrderByClause("sort asc");
//        if (goodsVO.getUserId() != null && !"".equals(goodsVO.getUserId())) {
//            criteria.andLike("userId", "%" + goodsVO.getUserId() + "%");
//        }

        List<Goods> goods = goodsMapper.selectByExample(o);
        List<GoodsInfoVO> categoryVOS = goodsConverter.converterToInfoVOList(goods);
        PageInfo<Goods> info = new PageInfo<>(goods);
        return new PageVO<>(info.getTotal(), categoryVOS);
    }





    /**
     * ๆทปๅ?ๅๅ
     * @param goodsVO
     */
    @Override
    public void add(GoodsVO goodsVO) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVO,goods);
        goods.setCreateTime(new Date());
        goods.setModifiedTime(new Date());
        goods.setStatus(1);

        goodsMapper.insert(goods);
    }


//    /**
//     * ็ผ่พๅๅ
//     * @param id
//     * @return
//     */
//    @Override
//    public ProductVO edit(Long id) {
//        Product product = productMapper.selectByPrimaryKey(id);
//        return ProductConverter.converterToProductVO(product);
//    }
//
    /**
     * ๆดๆฐๅๅ
     * @param id
     * @param goodsVO
     */
    @Override
    public void update(Long id, GoodsVO goodsVO) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVO,goods);
        goods.setModifiedTime(new Date());
        goodsMapper.updateByPrimaryKey(goods);
    }

    /**
     * ๅ?้คๅๅ
     * @param id
     */
    @Override
    public void delete(Long id) throws BusinessException {
        Goods t = new Goods();
        t.setId(id);
        Goods product = goodsMapper.selectByPrimaryKey(t);
        //ๅชๆ็ฉ่ตๅคไบๅๆถ็ซ,ๆ่ๅพๅฎกๆ?ธ็ๆๅตไธๅฏๅ?้ค
        if(product.getStatus()==2){
            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
        }else {
            goodsMapper.deleteByPrimaryKey(id);
        }
    }
//
//    /**
//     * ็ฉ่ตๅบๅญๅ่กจ
//     * @param pageNum
//     * @param pageSize
//     * @param productVO
//     * @return
//     */
//    @Override
//    public PageVO<ProductStockVO> findProductStocks(Integer pageNum, Integer pageSize, ProductVO productVO) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<ProductStockVO> productStockVOList=productStockMapper.findProductStocks(productVO);
//        PageInfo<ProductStockVO> info = new PageInfo<>(productStockVOList);
//        return new PageVO<>(info.getTotal(), productStockVOList);
//    }
//
//    /**
//     * ๆๆๅบๅญไฟกๆฏ
//     * @return
//     */
//    @Override
//    public List<ProductStockVO> findAllStocks(Integer pageNum, Integer pageSize, ProductVO productVO) {
//        PageHelper.startPage(pageNum, pageSize);
//        return productStockMapper.findAllStocks(productVO);
//    }
//
//    /**
//     * ็งปๅฅๅๆถ็ซ
//     * @param id
//     */
//    @Override
//    public void remove(Long id) throws BusinessException {
//        Product t = new Product();
//        t.setId(id);
//        Product product = productMapper.selectByPrimaryKey(t);
//        if(product.getStatus()!=0){
//            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
//        }else {
//            t.setStatus(1);
//            productMapper.updateByPrimaryKeySelective(t);
//        }
//    }
//
//    /**
//     * ไปๅๆถ็ซๆขๅคๆฐๆฎ
//     * @param id
//     */
//    @Override
//    public void back(Long id) throws BusinessException {
//        Product t = new Product();
//        t.setId(id);
//        Product product = productMapper.selectByPrimaryKey(t);
//        if(product.getStatus()!=1){
//            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
//        }else {
//            t.setStatus(0);
//            productMapper.updateByPrimaryKeySelective(t);
//        }
//    }
//
    /**
     * ็ฉ่ตๅฎกๆ?ธ
     * @param id
     */
    @Override
    public void publish(Long id) throws BusinessException {
        Goods t = new Goods();
        t.setId(id);
        Goods goods = goodsMapper.selectByPrimaryKey(t);


        //0:ๅ?้ค,1:ๅๅปบไฟฎๆน,2:ๅฎกๆ?ธไธญ,3:ๅฎกๆ?ธ้่ฟ
        if(goods.getStatus()!=1){
            throw new BusinessException(BusinessCodeEnum.PRODUCT_STATUS_ERROR);
        }else {
            t.setStatus(2);
            goodsMapper.updateByPrimaryKeySelective(t);
        }
    }



}
