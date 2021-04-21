package com.coderman.common.vo.business;

import com.coderman.common.model.project.ProjectTask;

import java.util.List;

public class ChartData {

    private String name;
    private int value;

    private List<ProjectTask> list;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<ProjectTask> getList() {
        return list;
    }

    public void setList(List<ProjectTask> list) {
        this.list = list;
    }
}


