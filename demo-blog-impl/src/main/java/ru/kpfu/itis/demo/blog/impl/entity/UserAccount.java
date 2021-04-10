package ru.kpfu.itis.demo.blog.impl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    String id;
    String displayName;
    String firstName;
    String lastName;
    String email;
    String userName;

    @Enumerated(EnumType.STRING)
    OAuthProviderEnum provider;

    public enum OAuthProviderEnum {
        FACEBOOK
    }
}
