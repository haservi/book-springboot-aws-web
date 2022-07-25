package com.haservi.book.config.auth.dto;

import com.haservi.book.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// 직렬화 구현
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
