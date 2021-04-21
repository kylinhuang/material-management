package com.coderman.common.model.project;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

public class Task {

    public String productId;  // 物资

    public String productNumber;  // 物资 数量

    public String goodsId;  // 商品 id

    public String warehousingId;  // 仓储 id

}
