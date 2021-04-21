package com.coderman.business.converter;

import com.coderman.common.model.project.Project;
import com.coderman.common.model.system.User;
import com.coderman.common.vo.business.ProjectVO;
import com.coderman.system.mapper.UserMapper;
import org.bouncycastle.crypto.tls.UserMappingType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:22
 * @Version 1.0
 **/
@Component
public class ProjectConverter {

    @Autowired
    private UserMapper userMapper;

    /**
     * 转VOList
     * @param projects
     * @return
     */
    public  List<ProjectVO> converterToVOList(List<Project> projects) {
        List<ProjectVO> projectVOS=new ArrayList<>();
        if(!CollectionUtils.isEmpty(projects)){
            for (Project project : projects) {
                ProjectVO projectVO = converterToProjectVO(project);
                projectVOS.add(projectVO);
            }
        }
        return projectVOS;
    }

    /**
     * 转VO
     * @param project
     * @return
     */
    public  ProjectVO converterToProjectVO(Project project) {
        ProjectVO productVO = new ProjectVO();

        if (null != project.getCreatorId()){
            User creatorUser = userMapper.selectByPrimaryKey(project.getCreatorId());
            if (null != creatorUser){
                productVO.setCreatorId(creatorUser.getId());
                productVO.setCreatorName(creatorUser.getUsername());
                productVO.setCreatorPhoneNumber(creatorUser.getPhoneNumber());

            }
        }

        if (null != project.getAllocatorId()){
            User allocatorUser = userMapper.selectByPrimaryKey(project.getAllocatorId());
            if (null != allocatorUser){
                productVO.setAllocatorId(allocatorUser.getId());
                productVO.setAllocatorName(allocatorUser.getUsername());
                productVO.setAllocatorPhoneNumber(allocatorUser.getPhoneNumber());
            }
        }


        BeanUtils.copyProperties(project,productVO);
        return productVO;
    }
}
