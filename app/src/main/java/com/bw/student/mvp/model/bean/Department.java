package com.bw.student.mvp.model.bean;

import com.bw.student.mvp.base.BaseResponse;

import java.util.List;

/**
 *
 *
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.model.bean
 * @time 2018/12/5 21:17
 */
public class Department extends BaseResponse<List<Department>> {


    /**
     * depName : 中原市场部
     * id : 1
     */

    private String depName;
    private int id;

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
