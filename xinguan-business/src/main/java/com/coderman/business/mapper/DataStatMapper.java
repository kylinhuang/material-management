package com.coderman.business.mapper;
import com.coderman.common.model.project.ProjectTask;
import com.coderman.common.vo.business.StatGoodsVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;


public interface DataStatMapper extends  Mapper<ProjectTask>  {

    /**
     * 物资统计
     * @param start
     * @param end
     * @param productId
     * @param supplierId
     * @return
     */
    List<StatGoodsVO> statProjectTask(@Param("start") Date start,
                                      @Param("end") Date end,
                                      @Param("productId") String  productId,
                                      @Param("supplierId") String  supplierId);


}
