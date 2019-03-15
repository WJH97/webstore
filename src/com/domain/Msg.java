package com.domain;

public class Msg {
    private String username;
    String nickname;
    String password;
    String email;
    Error error;

    public Msg() {
    }

    public Msg(String username, String nickname, String password, String email, Error error) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.error = error;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
