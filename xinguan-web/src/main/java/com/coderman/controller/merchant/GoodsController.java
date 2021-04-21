package com.coderman.controller.merchant;

import com.coderman.business.service.GoodsService;
import com.coderman.business.service.ProductService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.GoodsInfoVO;
import com.coderman.common.vo.business.GoodsVO;
import com.coderman.common.vo.business.ProductStockVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.system.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:19
 * @Version 1.0
 **/
@Api(tags = "商品模块-物资资料相关接口")
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    /**
     * 商品列表
     * @return
     */
    @ApiOperation(value = "物资列表", notes = "物资列表,根据物资名模糊查询")
    @GetMapping("/findList")
    public ResponseBean findProductList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "categorys", required = false) String categorys,
                                        GoodsVO goodsVO) {
//        buildCategorySearch(categorys, goodsVO);
        PageVO<GoodsVO> productVOPageVO = goodsService.findList(pageNum, pageSize, goodsVO);
        return ResponseBean.success(productVOPageVO);
    }






    /**
     * 商品列表
     * @return
     */
    @ApiOperation(value = "物资列表", notes = "物资列表,根据物资名模糊查询")
    @GetMapping("/findReviewList")
    public ResponseBean findReviewList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize") Integer pageSize ) {
//        buildCategorySearch(categorys, goodsVO);
        PageVO<GoodsInfoVO> productVOPageVO = goodsService.findReviewList(pageNum, pageSize);
        return ResponseBean.success(productVOPageVO);
    }




    /**findList
     * 商品列表
     * @return
     */
    @ApiOperation(value = "物资列表", notes = "物资列表,根据物资名模糊查询")
    @GetMapping("/findSelectList")
    public ResponseBean findGoodList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize") Integer pageSize,
                                        @RequestParam(value = "productId") Long productId) {
//        buildCategorySearch(categorys, goodsVO);
        PageVO<GoodsInfoVO> productVOPageVO = goodsService.findGoodList(pageNum, pageSize, productId);
        return ResponseBean.success(productVOPageVO);
    }

//    /**
//     * 可入库物资(入库页面使用)
//     * @return
//     */
//    @ApiOperation(value = "可入库物资列表", notes = "物资列表,根据物资名模糊查询")
//    @GetMapping("/findProducts")
//    public ResponseBean findProducts(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
//                                     @RequestParam(value = "pageSize") Integer pageSize,
//                                     @RequestParam(value = "categorys", required = false) String categorys,
//                                     ProductVO productVO){
//        productVO.setStatus(0);
//        buildCategorySearch(categorys, productVO);
//        PageVO<ProductVO> productVOPageVO = productService.findProductList(pageNum, pageSize, productVO);
//        return ResponseBean.success(productVOPageVO);
//    }

//    /**
//     * 库存列表
//     * @return
//     */
//    @ApiOperation(value = "库存列表", notes = "物资列表,根据物资名模糊查询")
//    @GetMapping("/findProductStocks")
//    public ResponseBean findProductStocks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
//                                        @RequestParam(value = "pageSize") Integer pageSize,
//                                        @RequestParam(value = "categorys", required = false) String categorys,
//                                        ProductVO productVO) {
//
//        buildCategorySearch(categorys, productVO);
//        PageVO<ProductStockVO> productVOPageVO = productService.findProductStocks(pageNum, pageSize, productVO);
//        return ResponseBean.success(productVOPageVO);
//    }



//    /**
//     * 所有库存(饼图使用)
//     * @return
//     */
//    @ApiOperation(value = "全部库存", notes = "物资所有库存信息,饼图使用")
//    @GetMapping("/findAllStocks")
//    public ResponseBean findAllStocks(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
//                                      @RequestParam(value = "pageSize") Integer pageSize,
//                                      @RequestParam(value = "categorys", required = false) String categorys,
//                                      ProductVO productVO) {
//        buildCategorySearch(categorys, productVO);
//        List<ProductStockVO> list = productService.findAllStocks(pageNum, pageSize,productVO);
//        return ResponseBean.success(list);
//    }



    /**
     * 封装物资查询条件
     * @param categorys
     * @param goodsVO
     */
    private void buildCategorySearch(@RequestParam(value = "categorys", required = false) String categorys, GoodsVO goodsVO) {
//        if (categorys != null && !"".equals(categorys)) {
//            String[] split = categorys.split(",");
//            switch (split.length) {
//                case 1:
//                    goodsVO.setOneCategoryId(Long.parseLong(split[0]));
//                    break;
//                case 2:
//                    goodsVO.setOneCategoryId(Long.parseLong(split[0]));
//                    goodsVO.setTwoCategoryId(Long.parseLong(split[1]));
//                    break;
//                case 3:
//                    goodsVO.setOneCategoryId(Long.parseLong(split[0]));
//                    goodsVO.setTwoCategoryId(Long.parseLong(split[1]));
//                    goodsVO.setThreeCategoryId(Long.parseLong(split[2]));
//                    break;
//            }
//        }
    }


    /**
     * 添加商品
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "添加商品失败", operation = "添加物资")
    @ApiOperation(value = "添加物资")
    @RequiresPermissions({"goods:add"})
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated GoodsVO goodsVO) throws BusinessException {


        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        goodsVO.setUserId(activeUser.getUser().getId());

        goodsService.add(goodsVO);


        return ResponseBean.success();
    }

//    /**
//     * 编辑物资
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "编辑物资", notes = "编辑物资信息")
//    @RequiresPermissions({"product:edit"})
//    @GetMapping("/edit/{id}")
//    public ResponseBean edit(@PathVariable Long id) {
//        ProductVO productVO = productService.edit(id);
//        return ResponseBean.success(productVO);
//    }
//
    /**
     * 更新物资
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新物资失败", operation = "物资资料更新")
    @ApiOperation(value = "更新物资", notes = "更新物资信息")
    @RequiresPermissions({"goods:update"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody GoodsVO goodsVO) throws BusinessException {
        goodsService.update(id, goodsVO);
        return ResponseBean.success();
    }

    /**
     * 删除物资
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "删除物资失败", operation = "物资资料删除")
    @ApiOperation(value = "删除物资", notes = "删除物资信息")
    @RequiresPermissions({"goods:delete"})
    @DeleteMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws BusinessException {
        goodsService.delete(id);
        return ResponseBean.success();
    }
//
//
//
//    /**
//     * 移入回收站
//     * @param id
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "回收物资失败", operation = "物资资料回收")
//    @ApiOperation(value = "移入回收站", notes = "移入回收站")
//    @RequiresPermissions({"product:remove"})
//    @PutMapping("/remove/{id}")
//    public ResponseBean remove(@PathVariable Long id) throws BusinessException {
//        productService.remove(id);
//        return ResponseBean.success();
//    }
    /**
     * 商品添加审核
     * @param id
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "商品添加审核失败", operation = "商品资料审核")
    @ApiOperation(value = "添加审核", notes = "商品添加审核")
    @RequiresPermissions({"goods:publish"})
    @PutMapping("/publish/{id}")
    public ResponseBean publish(@PathVariable Long id) throws BusinessException {
        goodsService.publish(id);
        return ResponseBean.success();
    }
//    /**
//     * 恢复数据从回收站
//     * @param id
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "恢复物资失败", operation = "物资资料恢复")
//    @ApiOperation(value = "恢复物资", notes = "从回收站中恢复物资")
//    @RequiresPermissions({"product:back"})
//    @PutMapping("/back/{id}")
//    public ResponseBean back(@PathVariable Long id) throws BusinessException {
//        productService.back(id);
//        return ResponseBean.success();
//    }
}


