package com.coderman.common.enums.system;

/**
 * @Author zhangyukang
 * @Date 2020/5/29 16:52
 * @Version 1.0
 **/


public enum ProjectTaskStatusEnum {
    delete(0,"删除"), // 删除
    create(1,"创建"), // 创建
    to_be_shipped(2,"待发货"), // 待发货
    shipped(3,"已发货"), // 已发货
    warehousing(4,"入库"), // 入库
    reject(5,"驳回"), // 驳回
    audit_price(6,"审核价格");


    private int id;
    private String messgae;

    ProjectTaskStatusEnum(int id, String messgae) {
        this.id = id;
        this.messgae = messgae;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
