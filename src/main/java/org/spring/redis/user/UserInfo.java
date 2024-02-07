package org.spring.redis.user;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
public class UserInfo {

    private String email;
    private String password;
    private String name;


    public UserInfo() {

    }

    @Builder
    public UserInfo(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(email, userInfo.email) && Objects.equals(password, userInfo.password) && Objects.equals(name, userInfo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, name);
    }

}
