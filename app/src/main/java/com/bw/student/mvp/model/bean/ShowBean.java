package com.bw.student.mvp.model.bean;

import com.bw.student.mvp.base.BaseResponse;

import java.util.List;

/**
 * @author Administrator QQ:1228717266
 * @name BwStudent
 * @class name：com.bw.student.mvp.model.bean
 * @time 2018/12/7 13:02
 */
public class ShowBean extends BaseResponse<List<ShowBean>> {


    /**
     * answer : 不错
     * id : 1
     * problem : 前景如何
     */

    private String answer;
    private int id;
    private String problem;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
