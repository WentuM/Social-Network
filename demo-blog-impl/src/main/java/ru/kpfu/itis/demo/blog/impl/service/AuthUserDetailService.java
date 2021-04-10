package ru.kpfu.itis.demo.blog.impl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.demo.blog.impl.entity.UserAccount;
import ru.kpfu.itis.demo.blog.impl.entity.UserEntity;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UserAccountRepository;
import ru.kpfu.itis.demo.blog.impl.jpa.repository.UsersRepository;

import java.util.Collections;

import static org.springframework.util.StringUtils.hasText;
@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements SocialUserDetailsService, ConnectionSignUp {
    private final UsersRepository userRepository;
    private final UserAccountRepository userAccountRepository;
//    private final FBService fbService;


    @Override
    public SocialUserDetails loadUserByUserId(String accountId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByAccountId(accountId).orElseThrow(() -> new UsernameNotFoundException(accountId));
        return new SocialUser(
                user.getUserAccount().getId(),
                "",
                true,
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    @Override
    public String execute(Connection<?> connection) {
        UserProfile profile = connection.fetchUserProfile();
        return userRepository.findByAccountId(getProfileId(profile)).isPresent() ? null : registerUser(connection).getAccountId();
    }

    private UserEntity registerUser(Connection<?> connection) {
//        UserProfile profile = connection.fetchUserProfile();
        String email = connection.fetchUserProfile().getEmail();
        String id = connection.fetchUserProfile().getId();
        String username = connection.fetchUserProfile().getUsername();
//        UserProfile profile = fbService.getProfile("me", connection.createData().getAccessToken());
        UserAccount userAccount = userAccountRepository.save(
                UserAccount.builder()
                        .id(id)
                        .email(email)
                        .userName(username)
//                        .firstName(profile.getFirstName())
//                        .lastName(profile.getLastName())
                        .displayName(connection.getDisplayName())
                        .provider(UserAccount.OAuthProviderEnum.valueOf(connection.getKey().getProviderId().toUpperCase()))
                        .build()
        );

        UserEntity user = new UserEntity();
        user.setName(username);
//        user.setLastName(profile.getLastName());
        user.setEmail(email);
        user.setAccountId(userAccount.getId());
        return userRepository.save(user);
    }

    private String getProfileId(UserProfile profile) {
        if (hasText(profile.getId())) return profile.getId();
        if (hasText(profile.getEmail())) return profile.getEmail();
        if (hasText(profile.getUsername())) return profile.getUsername();

        throw new IllegalArgumentException("can't fetch user ID");
    }
}
