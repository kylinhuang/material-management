package com.coderman.business.converter;

import com.coderman.business.mapper.GoodsMapper;
import com.coderman.business.mapper.ProductMapper;
import com.coderman.business.mapper.ProjectMapper;
import com.coderman.common.model.business.Goods;
import com.coderman.common.model.business.Product;
import com.coderman.common.model.business.Supplier;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.project.ProjectTask;
import com.coderman.common.model.project.Task;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.GoodsVO;
import com.coderman.common.vo.business.ProductVO;
import com.coderman.common.vo.business.ProjectTaskVO;
import com.coderman.common.vo.business.ProjectVO;
import com.coderman.common.vo.system.UserVO;
import com.coderman.system.converter.UserConverter;
import com.coderman.system.mapper.UserMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:22
 * @Version 1.0
 **/
@Component
public class ProjectTaskConverter {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsConverter goodsConverter;



    @Autowired
    private SupplierConverter supplierConverter;


    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 转VOList
     * @param projectTasks
     * @return
     */
    public  List<ProjectTaskVO> converterToVOList(List<ProjectTask> projectTasks) {
        List<ProjectTaskVO> projectTaskVOList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(projectTasks)){
            for (ProjectTask projectTask : projectTasks) {
                ProjectTaskVO projectTaskVO = converterToVO(projectTask);
                projectTaskVOList.add(projectTaskVO);
            }
        }
        return projectTaskVOList;
    }


    /**
     * 转VO
     * @param task
     * @return
     */
    public  ProjectTaskVO converterToVO(ProjectTask task) {
        ProjectTaskVO projectTaskVO = new ProjectTaskVO();
        BeanUtils.copyProperties(task,projectTaskVO);




        // 项目名称
        Project project = projectMapper.selectByPrimaryKey(task.getProjectId());
        projectTaskVO.setProjectName(project.getProjectName());

        // 物资
        Product product = productMapper.selectByPrimaryKey(task.getProductId());
        projectTaskVO.setProduct(productConverter.converterToProductVO(product));



        // 供应商
        User supplier = userMapper.selectByPrimaryKey(task.getSupplierId());
        projectTaskVO.setSupplier(supplierConverter.converterToSupplierSelectVO(supplier,product.getId()));


        // 仓储
        User warehousing = userMapper.selectByPrimaryKey(task.getWarehousingId());
        projectTaskVO.setWarehousing(userConverter.converterToUserVO(warehousing));


        if (null != projectTaskVO.getWarehousingNumber() && null != projectTaskVO.getReviewPrice()){
            projectTaskVO.setTotalPrice(projectTaskVO.getReviewPrice().multiply(new BigDecimal(projectTaskVO.getWarehousingNumber())));
        }



        return projectTaskVO;
    }

//    /**
//     * 转VO
//     * @param projectTask
//     * @return
//     */
//    public ArrayList<ProjectTaskVO> converterToProjectVO(ProjectTask projectTask) {
//
//        String taskList = projectTask.getTaskList();
//
//        ArrayList<Task> tasks =  getTaskList(taskList);
//
//        ArrayList<ProjectTaskVO> list = getTaskInfo(tasks);
//
//        return list;
//
//    }
//
//    private ArrayList<ProjectTaskVO> getTaskInfo(ArrayList<Task> tasks) {
//        ArrayList<ProjectTaskVO> list  =  new ArrayList<ProjectTaskVO>();
//
//        for(Task task : tasks ){
//            ProjectTaskVO projectTaskVO =  new ProjectTaskVO();
//
//            // 仓储
//            User warehousing = userMapper.selectByPrimaryKey(task.warehousingId);
//            UserVO warehousingUserVO = new UserVO();
//            BeanUtils.copyProperties(warehousing,warehousingUserVO);
//            projectTaskVO.setWarehousing(warehousingUserVO);
//
//
//            // 物资
//            Product product = productMapper.selectByPrimaryKey(task.productId);
//            ProductVO productVO = new ProductVO();
//            BeanUtils.copyProperties(product,productVO);
//            projectTaskVO.setProductVO(productVO);
//
//            // 商品
//            Goods goods = goodsMapper.selectByPrimaryKey(task.goodsId);
//            GoodsVO goodsVO = new GoodsVO();
//            BeanUtils.copyProperties(goods,goodsVO);
//            projectTaskVO.setGoodsVO(goodsVO);
//
//            // 商户
//            User merchant = userMapper.selectByPrimaryKey(goods.getUserId());
//            UserVO merchantUserVO = new UserVO();
//            BeanUtils.copyProperties(merchant,merchantUserVO);
//            projectTaskVO.setWarehousing(merchantUserVO);
//
//            list.add(projectTaskVO);
//        }
//
//        return list;
//    }

    private ArrayList<Task> getTaskList(String taskList) {
        Gson gson = new Gson();
        JsonParser parser =new JsonParser();
        JsonArray Jarray = parser.parse(taskList).getAsJsonArray();

        ArrayList<Task> lcs =new ArrayList<Task>();

        for(JsonElement obj : Jarray ){
            Task cse = gson.fromJson( obj , Task.class);
            lcs.add(cse);
        }
        return lcs ;

    }

    public String  getCategoryKeys( int[] categoryKeys) {
        String keys = "";
        if (null == categoryKeys ) return "";
        for (int key :categoryKeys){
            keys =keys + key +",";
        }
        return keys;
    }



}
