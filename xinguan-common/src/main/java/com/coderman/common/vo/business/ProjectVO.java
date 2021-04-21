package com.coderman.common.vo.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:16
 * @Version 1.0
 **/
@Data
public class ProjectVO {

    private Long id;

    private String projectNum; //项目编号

    @NotBlank
    private String projectName; //项目名称

    private String remark;

    private Integer sort = 1;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date modifiedTime;

    private String imageUrl;


    private String creatorName; //创建者  (项目部)
    private Long creatorId; //创建者id (项目部)
    private String creatorPhoneNumber; //创建者 电话 (项目部)


    private String allocatorName; //分配者  (资源管理部)
    private Long allocatorId; //分配者id (资源管理部)
    private String allocatorPhoneNumber; //分配者 电话 (项目部)


    private Integer status;//0:项目创建 ,1:项目指派分配, 2:项目指派采购, 3:项目入库完成, 4:项目验收完成

    private Integer deleteStatus = 1;//是否有效  0:无效   1:有效

}
