package com.coderman.controller.business;

import com.coderman.business.service.WarehousingSupplierInRecordService;
import com.coderman.business.service.WarehousingSupplierService;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.WarehousingSupplierInRecordVO;
import com.coderman.common.vo.business.WarehousingSupplierReq;
import com.coderman.common.vo.business.WarehousingSupplierVO;
import com.coderman.common.vo.system.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 来源管理
 *
 * @Author zhangyukang
 * @Date 2020/3/16 20:18
 * @Version 1.0
 **/
@Api(tags = "业务模块-仓库物资相关接口")
@RestController
@RequestMapping("/warehousingSuppliesInRecord")
public class WarehousingSuppliesInRecordController {


    @Autowired
    private WarehousingSupplierInRecordService warehousingSupplierInRecordService;


    /**
     * 所有物资
     *
     * @return
     *
     */
    @ApiOperation(value = "所有物资", notes = "所有物资,根据条件查询")
    @GetMapping("/allList")
    public ResponseBean allList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize") Integer pageSize,
                             WarehousingSupplierReq warehousingSupplierReq) {
        PageVO<WarehousingSupplierInRecordVO> supplierVOPageVO = warehousingSupplierInRecordService.allList(pageNum, pageSize, warehousingSupplierReq);
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
        PageVO<WarehousingSupplierInRecordVO> supplierVOPageVO = warehousingSupplierInRecordService.list(pageNum, pageSize, warehousingSupplierReq);
        return ResponseBean.success(supplierVOPageVO);
    }

}
