package com.coderman.common.enums.system;

/**
 * @Author zhangyukang
 * @Date 2020/5/29 16:52
 * @Version 1.0
 **/


public enum RoleEnum {
    ProjectManage(146), // 项目管理
    MaterialManagement(147), // 物资管理
    WarehouseManagement(148), // 仓储管理
    supplier(149); // 供应商

    private int id;

    RoleEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
