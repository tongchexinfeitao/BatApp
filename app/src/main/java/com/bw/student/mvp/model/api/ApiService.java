package com.bw.student.mvp.model.api;

import com.bw.student.mvp.base.BaseResponse;
import com.bw.student.mvp.model.bean.Condition;
import com.bw.student.mvp.model.bean.Department;
import com.bw.student.mvp.model.bean.ShowBean;

import java.util.HashMap;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * api数据
 *
 * @author Administrator QQ:1228717266
 * @name DimensionTech
 * @class name：com.wd.tech.mvp.model.api
 * @time 2018/11/29 22:41
 */
public interface ApiService {
    // TODO: 2018/12/5 市场部门列表
    @GET("enrollment/marketDepartment/verify/v1/findAllMarketDepartment")
    Observable<BaseResponse<List<Department>>> findAllMarketDepartment(@Header("imei") String imei);

    // TODO: 2018/12/5  学院风采展示
    @GET("enrollment/campusStyle/verify/v1/campusStyleShow")
    Observable<BaseResponse> campusStyleShow(@Header("imei") String imei);

    // TODO: 2018/12/5 就业学生信息展示列表 
    @GET("enrollment/employmentStudents/verify/v1/findStudentsByCondition")
    Observable<BaseResponse<List<Condition>>> findStudentsByCondition(@Header("imei") String imei, @QueryMap HashMap<String,String> map);


    @GET("enrollment/problemAnswer/verify/v1/problemAnswerShow")
    Observable<BaseResponse<List<ShowBean>>> problemAnswerShow(@Header("imei") String imei, @Query("page") int page, @Query("count") int count);

    @FormUrlEncoded
    @POST("enrollment/consult/verify/v1/addConsult")
    Observable<BaseResponse> addConsult(@Header("imei") String imei, @FieldMap HashMap<String,String> map);
}
