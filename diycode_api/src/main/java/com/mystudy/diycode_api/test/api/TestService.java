package com.mystudy.diycode_api.test.api;

import com.mystudy.diycode_api.test.been.Hello;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestService {
//--- 测试接口 -------------------------------------------------------------------------------

    /**
     * 测试 token 是否正常
     *
     * @param limit 极限值
     * @return Hello 实体类
     */
    @GET("hello.json")
    Observable<Hello> hello(@Query("limit") Integer limit);
}
