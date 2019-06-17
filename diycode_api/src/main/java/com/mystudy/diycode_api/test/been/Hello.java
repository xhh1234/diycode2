package com.mystudy.diycode_api.test.been;

import java.io.Serializable;

/**
 * 测试(测试 token 是否管用)
 */
public class Hello implements Serializable {

    private int id;             // 当前用户唯一 id
    private String login;       // 当前用户登录用户名
    private String name;        // 当前用户昵称
    private String avatar_url;  // 当前用户的头像链接

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getAvatar_url() {
        return this.avatar_url;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
