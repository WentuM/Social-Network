package ru.kpfu.itis.demo.blog.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kpfu.itis.demo.blog.web.service.SignUpService;
import ru.kpfu.itis.demo.blog.web.security.config.jwt.JwtFilter;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2User;
import ru.kpfu.itis.demo.blog.web.security.ouath2.CustomOAuth2UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableOAuth2Sso
public class SecurityConfig {

    @Order(1)
    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        @Qualifier("customUserDetailService")
        private UserDetailsService userDetailsService;

        @Autowired
        private DataSource dataSource;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.httpBasic().disable()
                    .csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/actuator/**").permitAll()
                    .antMatchers("/signUp").anonymous()
                    .antMatchers("/signIn").anonymous()
                    .antMatchers("/home").authenticated()
                    .antMatchers("/profile").authenticated()
                    .antMatchers("/single").authenticated()
                    .antMatchers("/static/**").permitAll()
                    .antMatchers("/geocode").permitAll()
                    .antMatchers("/oauth2").permitAll()
                    .antMatchers("/", "/signIn", "/oauth/**").permitAll()
                    .antMatchers("/followUser/**").permitAll()
                    .antMatchers("/index").authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/signIn")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/home")
                    .failureUrl("/signIn?error")
                    .and()
                    .oauth2Login().loginPage("/signIn").userInfoEndpoint().userService(oauthUserService)
                    .and()
                    .successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                            signUpService.signUpOauth(oAuth2User.getEmail(), oAuth2User.getName());
                            httpServletResponse.sendRedirect("/home");
                        }
                    })
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");

        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
            JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
            jdbcTokenRepository.setDataSource(dataSource);
            return jdbcTokenRepository;
        }

        @Autowired
        private CustomOAuth2UserService oauthUserService;

        @Autowired
        private SignUpService signUpService;
    }

    @Order(2)
    @Configuration
    public static class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        @Qualifier("customUserDetailService")
        private UserDetailsService userDetailsService;

        @Autowired
        private DataSource dataSource;

        @Autowired
        private JwtFilter jwtFilter;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.httpBasic().disable()
                    .csrf().disable();
            http.authorizeRequests()
                    //api
                    .antMatchers("/actuator/**").permitAll()
                    .antMatchers("/api/posts/**").hasRole("USER")
                    .antMatchers("/api/auth").permitAll()
                    .antMatchers("/api/profile/**").hasRole("USER")
                    .and()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }
    }
}
