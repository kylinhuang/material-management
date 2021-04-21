package com.coderman.controller.business;

import com.coderman.business.service.SupplierService;
import com.coderman.business.service.WarehousingSupplierService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessException;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 来源管理
 *
 * @Author zhangyukang
 * @Date 2020/3/16 20:18
 * @Version 1.0
 **/
@Api(tags = "业务模块-仓库物资相关接口")
@RestController
@RequestMapping("/warehousingSupplies")
public class WarehousingSuppliesController {


    @Autowired
    private WarehousingSupplierService warehousingSupplierService;


    /**
     * 所有物资
     *
     * @return
     */
    @ApiOperation(value = "所有物资", notes = "所有物资,根据条件查询")
    @GetMapping("/allList")
    public ResponseBean allList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize") Integer pageSize,
                             WarehousingSupplierReq warehousingSupplierReq) {
        PageVO<WarehousingSupplierVO> supplierVOPageVO = warehousingSupplierService.allList(pageNum, pageSize, warehousingSupplierReq);
        return ResponseBean.success(supplierVOPageVO);
    }





    /**
     * 仓库物资
     *
     * @return
     */
    @ApiOperation(value = "仓库物资", notes = "仓库物资,根据条件模糊查询")
    @GetMapping("/list")
    public ResponseBean list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize") Integer pageSize,
                             WarehousingSupplierReq warehousingSupplierReq) {
        PageVO<WarehousingSupplierVO> supplierVOPageVO = warehousingSupplierService.list(pageNum, pageSize, warehousingSupplierReq);
        return ResponseBean.success(supplierVOPageVO);
    }




    /**
     * 领取物资
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "领取物资更新失败", operation = "领取物资")
    @ApiOperation(value = "领取物资", notes = "领取物资")
    @PutMapping("/receive/{id}")
    public ResponseBean receive(@PathVariable Long id, @RequestBody @Validated WarehousingSupplierOutRecordVO warehousingSupplierOutRecordVO) throws BusinessException{
        warehousingSupplierService.receive(id, warehousingSupplierOutRecordVO);
        return ResponseBean.success();
    }




//    /**
//     * 添加来源
//     *
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "物资来源添加失败", operation = "物资来源添加")
//    @RequiresPermissions({"supplier:add"})
//    @ApiOperation(value = "添加来源")
//    @PostMapping("/add")
//    public ResponseBean add(@RequestBody @Validated SupplierVO supplierVO) {
//        warehousingSupplierService.add(supplierVO);
//        return ResponseBean.success();
//    }

//    /**
//     * 编辑来源
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "编辑来源", notes = "编辑来源信息")
//    @RequiresPermissions({"supplier:edit"})
//    @GetMapping("/edit/{id}")
//    public ResponseBean<SupplierVO> edit(@PathVariable Long id) {
//        SupplierVO supplierVO = warehousingSupplierService.edit(id);
//        return ResponseBean.success(supplierVO);
//    }

//    /**
//     * 更新来源
//     *
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "物资来源更新失败", operation = "物资来源更新")
//    @ApiOperation(value = "更新来源", notes = "更新来源信息")
//    @RequiresPermissions({"supplier:update"})
//    @PutMapping("/update/{id}")
//    public ResponseBean update(@PathVariable Long id, @RequestBody @Validated SupplierVO supplierVO) {
//        warehousingSupplierService.update(id, supplierVO);
//        return ResponseBean.success();
//    }

//    /**
//     * 删除来源
//     *
//     * @param id
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "物资来源删除失败", operation = "物资来源删除")
//    @ApiOperation(value = "删除来源", notes = "删除来源信息")
//    @RequiresPermissions({"supplier:delete"})
//    @DeleteMapping("/delete/{id}")
//    public ResponseBean delete(@PathVariable Long id) {
//        warehousingSupplierService.delete(id);
//        return ResponseBean.success();
//    }

//    /**
//     * 所有来源
//     *
//     * @return
//     */
//    @ApiOperation(value = "所有来源", notes = "所有来源列表")
//    @GetMapping("/findAll")
//    public ResponseBean<List<SupplierVO>> findAll() {
//        List<SupplierVO> supplierVOS = warehousingSupplierService.findAll();
//        return ResponseBean.success(supplierVOS);
//    }
}
