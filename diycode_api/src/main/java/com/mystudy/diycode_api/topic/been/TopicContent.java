

package com.mystudy.diycode_api.topic.been;


import com.mystudy.diycode_api.base.been.Abilities;
import com.mystudy.diycode_api.user.User;

import java.io.Serializable;

/**
 * Topic 详情
 */
public class TopicContent implements Serializable {
    private int id;                         // 唯一 id
    private String title;                   // 标题
    private String created_at;              // 创建时间
    private String updated_at;              // 更新时间
    private String replied_at;              // 最近一次回复时间
    private int replies_count;              // 回复总数量
    private String node_name;               // 节点名称
    private int node_id;                    // 节点 id
    private int last_reply_user_id;         // 最近一次回复的用户 id
    private String last_reply_user_login;   // 最近一次回复的用户登录名
    private User user;                      // 创建该话题的用户(信息)
    private boolean deleted;                // 是否是被删除的
    private boolean excellent;              // 是否是加精的
    private Abilities abilities;            // 当前用户对该话题拥有的权限
    private String body;                    // 话题详情(Markdown格式)
    private String body_html;               // 话题详情(HTML 格式)
    private int hits;                       // 浏览次数
    private int likes_count;                // 喜欢的人数
    private String suggested_at;            // 置顶(推荐)时间
    private Boolean followed;               // 是否关注
    private Boolean liked;                  // 是否喜欢
    private Boolean favorited;              // 是否收藏


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return this.updated_at;
    }

    public void setReplied_at(String replied_at) {
        this.replied_at = replied_at;
    }

    public String getReplied_at() {
        return this.replied_at;
    }

    public void setReplies_count(int replies_count) {
        this.replies_count = replies_count;
    }

    public int getReplies_count() {
        return this.replies_count;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getNode_name() {
        return this.node_name;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public int getNode_id() {
        return this.node_id;
    }

    public void setLast_reply_user_id(int last_reply_user_id) {
        this.last_reply_user_id = last_reply_user_id;
    }

    public int getLast_reply_user_id() {
        return this.last_reply_user_id;
    }

    public void setLast_reply_user_login(String last_reply_user_login) {
        this.last_reply_user_login = last_reply_user_login;
    }

    public String getLast_reply_user_login() {
        return this.last_reply_user_login;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setExcellent(boolean excellent) {
        this.excellent = excellent;
    }

    public boolean getExcellent() {
        return this.excellent;
    }

    public void setAbilities(Abilities abilities) {
        this.abilities = abilities;
    }

    public Abilities getAbilities() {
        return this.abilities;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getBody_html() {
        return this.body_html;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getHits() {
        return this.hits;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getLikes_count() {
        return this.likes_count;
    }

    public void setSuggested_at(String suggested_at) {
        this.suggested_at = suggested_at;
    }

    public String getSuggested_at() {
        return this.suggested_at;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }
}