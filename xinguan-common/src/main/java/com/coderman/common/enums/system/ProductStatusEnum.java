package com.coderman.common.enums.system;

/**
 * @Author zhangyukang
 * @Date 2020/5/29 16:52
 * @Version 1.0
 **/


public enum ProductStatusEnum {
    delete(0), // 删除
    create(1), // 创建
    reviewing(2), // 审核中
    reviewed(3); // 审核通过

    private int id;

    ProductStatusEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
