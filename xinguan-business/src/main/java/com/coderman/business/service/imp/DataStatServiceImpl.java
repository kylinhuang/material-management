package com.coderman.business.service.imp;

import com.coderman.business.mapper.DataStatMapper;
import com.coderman.business.service.DataStatService;
import com.coderman.common.vo.business.StatGoodsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DataStatServiceImpl implements DataStatService {

    @Autowired
    private DataStatMapper dataStatMapper;

    @Override
    public List<StatGoodsVO> statGoodsData(Date start, Date end, String productId, String supplier) {

        return dataStatMapper.statProjectTask(start, end, productId, supplier);
    }
}
