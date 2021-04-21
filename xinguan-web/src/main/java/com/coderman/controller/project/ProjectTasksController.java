package com.coderman.controller.project;

import com.coderman.business.service.ProjectTaskService;
import com.coderman.common.annotation.ControllerEndpoint;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.project.ProjectTask;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.*;
import com.coderman.common.vo.system.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:19
 * @Version 1.0
 **/
@Api(tags = "项目模块-项目相关接口")
@RestController
@RequestMapping("/task")
public class ProjectTasksController {


    @Autowired
    private ProjectTaskService projectTaskService;

    /**
     * 全部项目列表
     * @return
     */
    @ApiOperation(value = "项目列表", notes = "项目列表,根据项目名模糊查询")
    @GetMapping("/findTask")
    public ResponseBean findTask(@RequestParam(value = "projectId") Integer projectId ) {
        List<ProjectTaskVO> productVOPageVO = projectTaskService.findTask(projectId);
        return ResponseBean.success(productVOPageVO);
    }




    /**
     * 我的任务列表
     * @return
     */
    @ApiOperation(value = "我的任务列表", notes = "我的任务列表,根据条件查询")
    @GetMapping("/findMyTask")
    public ResponseBean findMyTaskList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize") Integer pageSize,
                                       @RequestParam(value = "status", required = false) int status,
                                       @RequestParam(value = "projectId", required = false) Long projectId
    ) {
//        buildCategorySearch(categorys, projectVO);
        PageVO<ProjectTaskVO> productVOPageVO = projectTaskService.findMyTaskList(pageNum, pageSize,status,projectId);

        return ResponseBean.success(productVOPageVO);

    }


    /**
     * 我的任务列表
     * @return
     */
    @ApiOperation(value = "我的任务列表", notes = "我的任务列表,根据条件查询")
    @GetMapping("/taskList")
    public ResponseBean taskList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "status", required = false) int status,
            @RequestParam(value = "projectId", required = false) Long projectId,
            @RequestParam(value = "supplierId", required = false) Long supplierId
    ) {
//        buildCategorySearch(categorys, projectVO);
        PageVO<ProjectTaskVO> productVOPageVO = projectTaskService.taskList(pageNum, pageSize,status,projectId,supplierId);

        return ResponseBean.success(productVOPageVO);

    }









    /**
     * 更新项目
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新项目失败", operation = "项目资料更新")
    @ApiOperation(value = "更新物资", notes = "更新项目信息")
    @RequiresPermissions({"task:update"})
    @PutMapping("/updateList/{projectId}")
    public ResponseBean updateTaskList(@PathVariable Long projectId, @RequestBody ArrayList<ProjectTaskVO> list) throws BusinessException {
//        if (projectVO.getCategoryKeys().length != 3) {
//            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR,"物资需要3级分类");
//        }

        projectTaskService.updateList(projectId, list);


        return ResponseBean.success();
    }



    /**
     * 修改项目任务
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "更新项目失败", operation = "项目资料更新")
    @ApiOperation(value = "修改项目任务", notes = "更新项目信息")
    @RequiresPermissions({"task:edit"})
    @PutMapping("/edit/{taskid}")
    public ResponseBean updateTaskList(@PathVariable Long taskid, @RequestBody ProjectTaskVO task) throws BusinessException {
//        if (projectVO.getCategoryKeys().length != 3) {
//            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR,"物资需要3级分类");
//        }

        projectTaskService.editTask(taskid, task);


        return ResponseBean.success();
    }

//    [{"edit":true,"goodsId":76,"categorykeys":[87,88],"categorys":"87,88,87,88","productId":69,"productNumber":"1","warehousingId":212}]




    /**
     * 修改项目任务 状态
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "修改项目任务状态 失败", operation = "修改项目任务状态")
    @ApiOperation(value = "修改项目任务状态", notes = "修改项目任务状态")
    @RequiresPermissions({"task:editstatus"})
    @PutMapping("/editStatus/{taskid}")
    public ResponseBean editTaskStatus(@PathVariable Long taskid, @RequestBody ProjectTaskVO task) throws BusinessException {
        projectTaskService.editTaskStatus(taskid, task);

        return ResponseBean.success();
    }




    /**
     * 修改项目任务 状态
     *
     * @return
     */
    @ControllerEndpoint(exceptionMessage = "修改项目任务状态 失败", operation = "修改项目任务状态")
    @ApiOperation(value = "修改项目任务状态", notes = "修改项目任务状态")
    @RequiresPermissions({"task:update"})
    @PutMapping("/update/{taskid}")
    public ResponseBean update(@PathVariable Long taskid, @RequestBody ProjectTaskVO task) throws BusinessException {
        projectTaskService.update(taskid, task);

        return ResponseBean.success();
    }



    /**
     * 全部项目列表
     * @return
     */
    @ApiOperation(value = "获取项目分析", notes = "项目列表,根据项目名模糊查询")
    @GetMapping("/projectAnalysis")
    public ResponseBean projectAnalysis(@RequestParam(value = "projectId") Long projectId ) {
        ProjectAnalysisVO  productVOPageVO = projectTaskService.projectAnalysis(projectId);
        return ResponseBean.success(productVOPageVO);
    }





    /**
     * 可入库物资(入库页面使用)
     * @return
     */
    @ApiOperation(value = "可入库物资列表", notes = "物资列表,根据物资名模糊查询")
    @GetMapping("/priceAnalysis")
    public ResponseBean priceAnalysis(@RequestParam(value = "productId") Long productId ,
                                     @RequestParam(value = "supplierId") Long supplierId,
                                     @RequestParam(value = "startDate", required = false) String startDate,
                                      @RequestParam(value = "endDate", required = false) String endDate

    ){
//        productVO.setStatus(0);
//        buildCategorySearch(categorys, productVO);
        PriceAnalysisVO priceAnalysisVO = projectTaskService.priceAnalysis(productId, supplierId, startDate,endDate);


        return ResponseBean.success(priceAnalysisVO);

    }


//    /**
//     * 我的项目列表
//     * @return
//     */
//    @ApiOperation(value = "我的项目", notes = "项目列表,根据项目名模糊查询")
//    @GetMapping("/findMyProjectList")
//    public ResponseBean findMyProjectList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
//                                        @RequestParam(value = "pageSize") Integer pageSize,
//                                        @RequestParam(value = "categorys", required = false) String categorys,
//                                        ProjectVO projectVO) {
//        buildCategorySearch(categorys, projectVO);
//        PageVO<ProjectVO> productVOPageVO = projectService.findMyProjectList(pageNum, pageSize, projectVO);
//        return ResponseBean.success(productVOPageVO);
//
//    }
//
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
//
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
//
//
//
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
//
//
//    /**
//     * 添加项目
//     * @return
//     */
//    @ControllerEndpoint(exceptionMessage = "添加物资失败", operation = "物资资料添加")
//    @ApiOperation(value = "添加项目")
//    @RequiresPermissions({"project:add"})
//    @PostMapping("/add")
//    public ResponseBean add(@RequestBody @Validated ProjectVO projectVO) throws BusinessException {
//
//        ActiveUser activeUser = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
//        projectVO.setCreatorId(activeUser.getUser().getId());
//
//        projectVO.setStatus(0);
//        projectService.add(projectVO);
//
//        return ResponseBean.success();
//    }
//
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


