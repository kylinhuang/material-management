package com.coderman.common.model.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Table(name = "biz_project")
public class Project {


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")//此处加上注解
    private Long id;

    private String projectNum; //项目编号

    private String projectName; //项目名称

    private String remark;

    private Integer sort;

    private Date createTime;

    private Date modifiedTime;

    private String imageUrl;



    private Long creatorId; //创建者id (项目部)

    private Long allocatorId; //分配者id (资源管理部)


    private Integer status;//0:项目创建 ,1:项目指派分配, 2:项目指派采购, 3:项目入库完成, 4:项目验收完成

    private Integer deleteStatus = 1;//是否有效  0:无效   0:有效


}
