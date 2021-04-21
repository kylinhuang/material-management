package com.coderman.business.converter;

import com.coderman.common.model.merchant.MerchantInfo;
import com.coderman.common.model.project.Project;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.MerchantInfoVO;
import com.coderman.common.vo.business.ProjectVO;
import com.coderman.system.mapper.UserMapper;
import com.wuwenze.poi.annotation.ExcelField;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:22
 * @Version 1.0
 **/
@Component
public class MerchantConverter {


    @Autowired
    private UserMapper userMapper;


    /**
     * 转VOList
     * @param projects
     * @return
     */
    public  List<MerchantInfoVO> converterList(List<MerchantInfo> projects) {
        List<MerchantInfoVO> projectVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(projects)){
            for (MerchantInfo entity : projects) {
                MerchantInfoVO bean = converterToVO(entity);
                projectVOS.add(bean);
            }
        }
        return projectVOS;
    }

    /**
     * 转VO
     * @param project
     * @return
     */
    public  MerchantInfoVO converterToVO(MerchantInfo project) {
        MerchantInfoVO merchantInfoVO = new MerchantInfoVO();
        BeanUtils.copyProperties(project,merchantInfoVO);

        User userMerchant = userMapper.selectByPrimaryKey(project.getUserId());

        merchantInfoVO.setUsername(userMerchant.getUsername());
        merchantInfoVO.setNickname(userMerchant.getNickname());
        merchantInfoVO.setPhoneNumber(userMerchant.getPhoneNumber());

        merchantInfoVO.setProvince(userMerchant.getProvince());
        merchantInfoVO.setProvinceValue(userMerchant.getProvinceValue());

        merchantInfoVO.setCity(userMerchant.getCity());
        merchantInfoVO.setCityValue(userMerchant.getCityValue());

        merchantInfoVO.setOrigin(userMerchant.getOrigin());
        merchantInfoVO.setOriginValue(userMerchant.getOriginValue());

        merchantInfoVO.setAddress(userMerchant.getAddress());
        merchantInfoVO.setInBlackList(userMerchant.getInBlackList()==1);

        return merchantInfoVO;

    }
}
