package ru.kpfu.itis.demo.blog.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.util.Assert;

import java.util.Arrays;
import javax.sql.DataSource;

import static org.springframework.security.crypto.encrypt.Encryptors.noOpText;
import static org.springframework.security.crypto.encrypt.Encryptors.text;
import static org.springframework.util.StringUtils.hasText;

@EnableSocial
@Configuration
@RequiredArgsConstructor
public class SocialConfiguration implements SocialConfigurer {
    //хранит в себе информацию о приложение facebook, то есть (id клиента и clientSecret)
    private final DataSource dataSource;
    private final FacebookEnvironment facebookEnvironment;
    private final Environment environment;
    private final SocialEncryptEnvironment encryptEnv;
    @Autowired
    private final ConnectionSignUp connectionSignUpService;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(
                new FacebookConnectionFactory(facebookEnvironment.getClientId(),
                        facebookEnvironment.getClientSecret()));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        if (Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
            Assert.hasText(encryptEnv.getKey());
        }

        TextEncryptor encryptor = hasText(encryptEnv.getKey()) ? text(encryptEnv.getKey(), encryptEnv.getSalt()) : noOpText();
        JdbcUsersConnectionRepository connectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, encryptor);
        connectionRepository.setConnectionSignUp(connectionSignUpService);
        return connectionRepository;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator locator, UsersConnectionRepository repository) {
        return new ProviderSignInUtils(locator, repository);
    }
}
