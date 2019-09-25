package com.kaminskyi.helpdesk.config;

import com.kaminskyi.helpdesk.mapper.CustomUserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.ldap.domain}")
    private String domain;

    @Value("${spring.ldap.urls}")
    private String ldapUrl;

    @Value("${spring.ldap.embedded.base-dn}")
    public String baseDn;

    @Bean
    public UserDetailsContextMapper userDetailsContextMapper() {
        return new CustomUserMapper();
    }

    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider authenticationProvider = new
                ActiveDirectoryLdapAuthenticationProvider(domain, ldapUrl, baseDn);
        authenticationProvider.setConvertSubErrorCodesToExceptions(true);
        authenticationProvider.setUseAuthenticationRequestCredentials(true);
        authenticationProvider.setUserDetailsContextMapper(userDetailsContextMapper());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(activeDirectoryLdapAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/goodlogin")
                .permitAll()
        .and()
        .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login")
        .permitAll();
    }

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(ldapUrl);
        contextSource.setBase(baseDn);
        contextSource.setUserDn("intrasite");
        contextSource.setPassword("Temp1234");
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}
