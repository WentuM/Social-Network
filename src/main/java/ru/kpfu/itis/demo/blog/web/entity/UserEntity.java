package ru.kpfu.itis.demo.blog.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String hashPassword;
    transient private String hashPassword2;

//    private String accountId;

//    @OneToOne
//    @JoinColumn(name = "accountId", insertable = false, updatable = false)
//    UserAccount userAccount;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "account_followers",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "follower_id"))
    private Set<UserEntity> followUser = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Provider provider;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public enum Provider {
        LOCAL, GOOGLE
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }


}
