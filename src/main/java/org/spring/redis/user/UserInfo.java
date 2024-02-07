package org.spring.redis.user;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserInfo {

    private String email;
    private String password;
    private String name;

    @Builder
    public UserInfo(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
