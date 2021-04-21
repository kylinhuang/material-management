package com.coderman.controller.data;

import com.coderman.business.service.DataStatService;
import com.coderman.common.response.ResponseBean;
import com.coderman.common.vo.business.StatGoodsVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(tags = "数据分析模块-基础物资统计接口")
@RestController
@RequestMapping("/data/stat")
public class BaseStatController {

    @Autowired
    private DataStatService dataStatService;

    @GetMapping("/goods")
    public ResponseBean findProductStocks(@RequestParam(value = "startDate") String startDate ,
                                          @RequestParam(value = "endDate") String endDate,
                                          @RequestParam(value = "productId") String productId,
                                          @RequestParam(value = "supplierId", required = false) String supplierId) {
        try {
            Date start = DateUtils.parseDate(startDate, new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"});
            Date end = DateUtils.parseDate(endDate, new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"});

            List<StatGoodsVO> statGoodsVOS = dataStatService.statGoodsData(start, end, productId, supplierId);
            return ResponseBean.success(statGoodsVOS);
        }catch (Exception e){
            return ResponseBean.error(e);
        }
    }


}
