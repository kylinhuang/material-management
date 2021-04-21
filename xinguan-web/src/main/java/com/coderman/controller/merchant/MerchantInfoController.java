package com.coderman.controller.merchant;

import com.coderman.business.service.MerchantInfoService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessException;
import com.coderman.common.response.ActiveUser;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.MerchantInfoVO;
import com.coderman.common.vo.system.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:19
 * @Version 1.0
 **/
@Api(tags = "供应商信息 模块-项目相关接口")
@RestController
@RequestMapping("/merchantInfo")
public class MerchantInfoController {


    @Autowired
    private MerchantInfoService merchantInfoService;

    /**
     * 全部项目列表
     * @return
     */
    @ApiOperation(value = "供应商信息列表", notes = "项目列表,根据项目名模糊查询")
    @GetMapping("/findList")
    public ResponseBean findList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 MerchantInfoVO merchantInfoVO) {
//        buildCategorySearch(categorys, projectVO);
        PageVO<MerchantInfoVO> productVOPageVO = merchantInfoService.findList(pageNum, pageSize, merchantInfoVO);
        return ResponseBean.success(productVOPageVO);

    }

    /**
     * 我的商户信息
     * @return
     */
    @ApiOperation(value = "", notes = "项目列表,根据项目名模糊查询")
    @GetMapping("/info")
    public ResponseBean findMerchantInfo() {
        MerchantInfoVO merchantInfoVO = merchantInfoService.findMerchantInfo();
        return ResponseBean.success(merchantInfoVO);
    }



//    /**
//     * 编辑项目
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "编辑项目", notes = "编辑项目信息")
//    @RequiresPermissions({"project:edit"})
//    @GetMapping("/edit/{id}")
//    public ResponseBean edit(@PathVariable Long id) {
//        ProjectVO productVO = projectService.edit(id);
//        return ResponseBean.success(productVO);
//    }
//
    /**
     * 更新
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新项目失败", operation = "项目资料更新")
    @ApiOperation(value = "更新物资", notes = "更新项目信息")
//    @RequiresPermissions({"project:update"})
    @PutMapping("/update/{id}")
    public ResponseBean update(@PathVariable Long id, @RequestBody MerchantInfoVO merchantInfoVO) throws BusinessException {

        merchantInfoService.update(id, merchantInfoVO);
        return ResponseBean.success();

    }


    /**
     * 更新项目
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新项目失败", operation = "项目资料更新")
    @ApiOperation(value = "更新物资", notes = "更新项目信息")
//    @RequiresPermissions({"project:update"})
    @PutMapping("/updateStatus/{id}")
    public ResponseBean updateStatus(@PathVariable Long id, @RequestBody MerchantInfoVO merchantInfoVO) throws BusinessException {

        merchantInfoService.updateStatus(id, merchantInfoVO);
        return ResponseBean.success();

    }


//
//    /**
//     * 删除物资
//     * @param id
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "删除物资失败", operation = "物资资料删除")
//    @ApiOperation(value = "删除物资", notes = "删除物资信息")
//    @RequiresPermissions({"project:delete"})
//    @DeleteMapping("/delete/{id}")
//    public ResponseBean delete(@PathVariable Long id) throws BusinessException {
//        projectService.delete(id);
//        return ResponseBean.success();
//    }



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
////        productVO.setStatus(0);
////        buildCategorySearch(categorys, productVO);
////        PageVO<ProductVO> productVOPageVO = productService.findProductList(pageNum, pageSize, productVO);
////        return ResponseBean.success(productVOPageVO);
//
//        return null;
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
////        buildCategorySearch(categorys, productVO);
////        PageVO<ProductStockVO> productVOPageVO = productService.findProductStocks(pageNum, pageSize, productVO);
////        return ResponseBean.success(productVOPageVO);
//
//        return null;
//    }
//
//

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
////        buildCategorySearch(categorys, productVO);
////        List<ProductStockVO> list = productService.findAllStocks(pageNum, pageSize,productVO);
////        return ResponseBean.success(list);
//        return null;
//    }



//    /**
//     * 封装物资查询条件
//     * @param categorys
//     * @param projectVO
//     */
//    private void buildCategorySearch(@RequestParam(value = "categorys", required = false) String categorys, ProjectVO projectVO) {
////        if (categorys != null && !"".equals(categorys)) {
////            String[] split = categorys.split(",");
////            switch (split.length) {
////                case 1:
////                    projectVO.setOneCategoryId(Long.parseLong(split[0]));
////                    break;
////                case 2:
////                    projectVO.setOneCategoryId(Long.parseLong(split[0]));
////                    projectVO.setTwoCategoryId(Long.parseLong(split[1]));
////                    break;
////                case 3:
////                    projectVO.setOneCategoryId(Long.parseLong(split[0]));
////                    projectVO.setTwoCategoryId(Long.parseLong(split[1]));
////                    projectVO.setThreeCategoryId(Long.parseLong(split[2]));
////                    break;
////            }
////        }
//    }


    /**
     * 添加 商户资料
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "添加 商户资料失败", operation = "添加 商户资料")
    @ApiOperation(value = "添加项目")
//    @RequiresPermissions({"merchantInfo:add"})
    @PostMapping("/add")
    public ResponseBean add(@RequestBody @Validated MerchantInfoVO merchantVO) throws BusinessException {

        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();

        merchantVO.setUserId(activeUser.getUser().getId());

        merchantVO.setStatus(0);
        merchantVO.setSort(1);

        merchantInfoService.add(merchantVO);

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
//        projectService.remove(id);
//        return ResponseBean.success();
//    }
//    /**
//     * 物资添加审核
//     * @param id
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "物资添加审核失败", operation = "物资资料审核")
//    @ApiOperation(value = "物资添加审核", notes = "物资添加审核")
//    @RequiresPermissions({"product:publish"})
//    @PutMapping("/publish/{id}")
//    public ResponseBean publish(@PathVariable Long id) throws BusinessException {
//        projectService.publish(id);
//        return ResponseBean.success();
//    }
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
//        projectService.back(id);
//        return ResponseBean.success();
//    }
}


