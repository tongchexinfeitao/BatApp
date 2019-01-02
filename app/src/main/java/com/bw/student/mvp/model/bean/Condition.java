package com.bw.student.mvp.model.bean;

import com.bw.student.mvp.base.BaseResponse;

import java.util.List;

/**
 * 学生信息展示列表
 *
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.model.bean
 * @time 2018/12/5 21:06
 */
public class Condition extends BaseResponse<List<Condition>> {


    /**
     * companyName : 北京***技有限公司
     * id : 1
     * nativePlace : 河北省邯郸市
     * post : Android开发工程师
     * salary : 18000
     * stuName : 王同学
     * stuPhoto : http://172.17.8.100/images/small/default/user.jpg
     */

    private String companyName;
    private int id;
    private String nativePlace;
    private String post;
    private int salary;
    private String stuName;
    private String stuPhoto;
    private String graduationTime;

    public String getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(String graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhoto() {
        return stuPhoto;
    }

    public void setStuPhoto(String stuPhoto) {
        this.stuPhoto = stuPhoto;
    }
}
