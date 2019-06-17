package com.mystudy.diycode_api.topic.api;

import com.mystudy.diycode_api.base.been.State;
import com.mystudy.diycode_api.topic.been.Topic;
import com.mystudy.diycode_api.topic.been.TopicContent;
import com.mystudy.diycode_api.topic.been.TopicReply;

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

public interface TopicService {

    //--- topic ------------------------------------------------------------------------------------

    /**
     * 获取 topic 列表
     *
     * @param type    类型，默认 last_actived，可选["last_actived", "recent", "no_reply", "popular", "excellent"]
     * @param node_id 如果你需要只看某个节点的，请传此参数, 如果不传 则返回全部
     * @param offset  偏移数值，默认值 0
     * @param limit   数量极限，默认值 20，值范围 1..150
     * @return topic 列表
     */
    @GET("topics.json")
    Observable<List<Topic>> getTopicsList(@Query("type") String type, @Query("node_id") Integer node_id,
                                          @Query("offset") Integer offset, @Query("limit") Integer limit);


    /**
     * 创建一个新的 topic
     *
     * @param title   话题标题
     * @param body    话题内容, Markdown 格式
     * @param node_id 节点编号
     * @return 新话题的内容详情
     */
    @POST("topics.json")
    @FormUrlEncoded
    Observable<TopicContent> createTopic(@Field("title") String title, @Field("body") String body,
                                         @Field("node_id") Integer node_id);

    /**
     * 获取 topic 内容
     *
     * @param id topic 的 id
     * @return 内容详情
     */
    @GET("topics/{id}.json")
    Observable<TopicContent> getTopic(@Path("id") int id);


    /**
     * 更新(修改) topic
     *
     * @param id      要修改的话题 id
     * @param title   话题标题
     * @param body    话题内容, Markdown 格式
     * @param node_id 节点编号
     * @return 更新后的话题内容详情
     */
    @POST("topics/{id}.json")
    @FormUrlEncoded
    Observable<TopicContent> updateTopic(@Path("id") int id, @Field("title") String title,
                                   @Field("body") String body, @Field("node_id") Integer node_id);

    /**
     * 删除一个话题
     *
     * @param id 要删除的话题 id
     * @return 状态
     */
    @DELETE("topics/{id}.json")
    Observable<State> deleteTopic(@Path("id") int id);


    //--- topic collection -------------------------------------------------------------------------

    /**
     * 收藏话题
     *
     * @param id 被收藏的话题 id
     * @return 状态信息
     */
    @POST("topics/{id}/favorite.json")
    @FormUrlEncoded
    Observable<State> collectionTopic(@Path("id") int id);

    /**
     * 取消收藏话题
     *
     * @param id 被收藏的话题 id
     * @return 状态信息
     */
    @POST("topics/{id}/unfavorite.json")
    @FormUrlEncoded
    Observable<State> unCollectionTopic(@Path("id") int id);


    //--- topic watch ------------------------------------------------------------------------------

    /**
     * 关注话题
     *
     * @param id 话题 id
     * @return 状态
     */
    @POST("topics/{id}/follow.json")
    @FormUrlEncoded
    Observable<State> watchTopic(@Path("id") int id);

    /**
     * 取消关注话题
     *
     * @param id 话题 id
     * @return 状态
     */
    @POST("topics/{id}/unfollow.json")
    @FormUrlEncoded
    Observable<State> unWatchTopic(@Path("id") int id);


    //--- topic reply ------------------------------------------------------------------------------

    /**
     * 获取 topic 回复列表
     *
     * @param id     topic 的 id
     * @param offset 偏移数值 默认 0
     * @param limit  数量极限，默认值 20，值范围 1...150
     * @return 回复列表
     */
    @GET("topics/{id}/replies.json")
    Observable<List<TopicReply>> getTopicRepliesList(@Path("id") int id, @Query("offset") Integer offset,
                                                     @Query("limit") Integer limit);

    /**
     * 创建 topic 回帖(回复，评论)
     *
     * @param id   话题列表
     * @param body 回帖内容, Markdown 格式
     * @return 回复详情
     */
    @POST("topics/{id}/replies.json")
    @FormUrlEncoded
    Observable<TopicReply> createTopicReply(@Path("id") int id, @Field("body") String body);

    /**
     * 获取回帖的详细内容（一般用于编辑回帖的时候）
     *
     * @param id id
     * @return 回帖内容
     */
    @GET("replies/{id}.json")
    Observable<TopicReply> getTopicReply(@Path("id") int id);


    /**
     * 更新回帖
     *
     * @param id   id
     * @param body 回帖详情
     * @return 回帖内容
     */
    @POST("replies/{id}.json")
    @FormUrlEncoded
    Observable<TopicReply> updateTopicReply(@Path("id") int id, @Field("body") String body);

    /**
     * 删除回帖
     *
     * @param id id
     * @return 状态
     */
    @DELETE("replies/{id}.json")
    Observable<State> deleteTopicReply(@Path("id") int id);

    //--- topic ban --------------------------------------------------------------------------------

    /**
     * 屏蔽话题，移到 NoPoint 节点 (管理员限定)
     *
     * @param id 要屏蔽的话题 id
     * @return 状态
     */
    @POST("topics/{id}/ban.json")
    @FormUrlEncoded
    Observable<State> banTopic(@Path("id") int id);
}
