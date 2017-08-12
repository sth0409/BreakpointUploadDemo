package com.example.sth0409.breakpointuploaddemo;

import android.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by user on 2016/12/11.
 */
public class RestSource {

    private static final String TAG = RestSource.class.getSimpleName();
    private static String TOKEN;
    private static ApiService apiService;

    public static void setToken(String token) {
        TOKEN = token;
    }

    public static ApiService getApiService() {
        if (apiService == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
//            loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
//                    .addInterceptor(new LoginInterceptor())
                    .addInterceptor(loggingInterceptor)
                    .build();
            Retrofit client = new Retrofit.Builder()
                    .baseUrl(Constants.BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = client.create(ApiService.class);
        }
        return apiService;
    }

//    public Map<String, Object> createPhoneInfoMap() {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("dem", DeviceInfoUtil.getDem());
//        params.put("os", DeviceInfoUtil.getOs());
//        params.put("osv", DeviceInfoUtil.getOsv());
//        params.put("model", DeviceInfoUtil.getModel());
//        params.put("apv", DeviceInfoUtil.getVersionName(context));
//        params.put("mac", DeviceInfoUtil.getMAC(context));
//        params.put("imei", DeviceInfoUtil.getImei(context));
//        return params;
//    }

    public static Map<String, Object> createPostMap(String json) {
        Log.i(TAG, "OkHttp --->: " + json);
        Map<String, Object> params = new HashMap<String, Object>();
        Date nowDate = new Date();
        String time = DateUtil.toDate(nowDate, "yyyy-MM-dd HH");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(json).append(time);
        params.put("sign", SignUtil.SHA(stringBuffer.toString(), "SHA-512"));
        params.put("param", json);
        return params;
    }

    public static String createSign(String json) {
        Log.i(TAG,"createSign --->: " + json);
        Date nowDate = new Date();
        String time = DateUtil.toDate(nowDate, "yyyy-MM-dd HH");
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(json).append(time);
        return SignUtil.SHA(stringBuffer.toString(), "SHA-512");
    }

    /**
     * If both the original call and the call with refreshed token failed,it will probably keep failing, so don't try again.
     * count times ++
     *
     * @param response
     * @return
     */
    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    /**
     * check if already has auth header
     *
     * @param originalRequest
     */
    private static boolean alreadyHasAuthorizationHeader(Request originalRequest) {
        if (originalRequest.headers().toString().contains("Authorization")) {
            Log.w(TAG, "already add Auth header");
            return true;
        }
        return false;
    }

    /**
     * some request after login/oauth before logout
     * but they no need oauth,so do not add auth header
     *
     * @param originalRequest
     */
    private static boolean noNeedAuth(Request originalRequest) {
        if (originalRequest.headers().toString().contains("NoNeedAuthFlag")) {
            Log.d("WW", "no need auth !");
            return true;
        }
        return false;
    }

}
