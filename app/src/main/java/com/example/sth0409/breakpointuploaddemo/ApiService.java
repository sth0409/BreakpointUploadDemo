package com.example.sth0409.breakpointuploaddemo;


import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * 所有的具体的和业务相关的Http请求
 * <p>
 * <p>
 * <p>
 * Created by weixb on 2017/3/17.
 */
public interface ApiService {
    /**
     * 图库发表
     */
    @Multipart
    @POST("KPCX/appTnotes/photoPublished")
    Call<HttpResponse<CommonDataBean>> photoPublished(@PartMap Map<String, RequestBody> params);
}

