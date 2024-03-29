/*
 * Copyright 2017 GcsSloop
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Last modified 2017-03-08 01:01:18
 *
 * GitHub:  https://github.com/GcsSloop
 * Website: http://www.gcssloop.com
 * Weibo:   http://weibo.com/GcsSloop
 */

package com.mystudy.diycode_api.news.api;

import com.mystudy.diycode_api.base.been.Node;
import com.mystudy.diycode_api.base.been.State;
import com.mystudy.diycode_api.news.been.New;
import com.mystudy.diycode_api.news.been.NewReply;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {

    /**
     * 获取 news 列表
     *
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     * @return news 列表
     */
    @GET("news.json")
    Observable<List<New>> getNewsList(@Query("node_id") Integer node_id, @Query("offset") Integer offset,
                                      @Query("limit") Integer limit);

    /**
     * 创建一个 new (分享)
     *
     * @param title   标题
     * @param address 地址(网址链接)
     * @param node_id 节点 id
     * @return 结果
     */
    @POST("news.json")
    @FormUrlEncoded
    Observable<New> createNews(@Field("title") Integer title, @Field("address") Integer address,
                         @Field("node_id") Integer node_id);

    /**
     * 获取 news 回帖列表
     *
     * @param id     id
     * @param offset 偏移数值 默认 0
     * @param limit  数量极限，默认值 20，值范围 1...150
     * @return 回复列表
     */
    @GET("news/{id}/replies.json")
    Observable<List<NewReply>> getNewsRepliesList(@Path("id") int id, @Query("offset") Integer offset,
                                                  @Query("limit") Integer limit);

    /**
     * 创建 news 回帖 (暂不可用, 没有api)
     *
     * @param id   id
     * @param body 回帖内容， markdown格式
     * @return 回复
     */
    @Deprecated
    @POST("news/{id}/replies.json")
    Observable<NewReply> createNewsReply(@Path("id") int id, @Field("body") Integer body);

    /**
     * 获取 news 回帖详情
     *
     * @param id id
     * @return 详情
     */
    @GET("news_replies/{id}.json")
    Observable<NewReply> getNewsReply(@Path("id") int id);

    /**
     * 更新 news 回帖
     *
     * @param id   id
     * @param body 回帖内容
     * @return 回帖内容
     */
    @POST("news_replies/{id}.json")
    Observable<NewReply> updateNewsReply(@Path("id") int id, @Field("body") String body);

    /**
     * 删除 news 回帖
     *
     * @param id id
     * @return 状态
     */
    @DELETE("news_replies/{id}.json")
    Observable<State> deleteNewsReply(@Path("id") int id);

    /**
     * 获取 news 分类列表
     *
     * @return 分类列表
     */
    @GET("news/nodes.json")
    Observable<List<Node>> getNewsNodesList();
}
