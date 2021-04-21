package com.coderman.common.vo.business;

import com.coderman.common.model.project.ProjectTask;
import com.coderman.common.vo.system.UserVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Data
public class ProjectAnalysisVO {

    private Long id;

    private Long projectId;


    List<ChartData> chartData;





}
