package com.shijc.wanandroidkotlin.http;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shijc.wanandroidkotlin.common.constant.Constant;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidkotlin.http
 * @Description:
 * @date 2019/3/29 上午 11:14
 */
public class ApiStore {
    private static Retrofit retrofit;

    private static String baseUrl = Constant.REQUEST_BASE_URL;

    public static <T> T createApi(Class<T> service){
        return retrofit.create(service);
    }

    static {
        createProxy();
    }

    /**
     * 创建 retrofit 客户端
     */
    private static void createProxy() {

        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder();
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new HttpLoggingInterceptor())
                /*.cookieJar(new CookiesManager())*/;

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
    }


}
