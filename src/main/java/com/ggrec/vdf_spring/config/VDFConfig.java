package com.ggrec.vdf_spring.config;

import com.ggrec.vdf_spring.security.SocialAuthenticationSuccessHandler;
import com.ggrec.vdf_spring.security.VDFAuthenticationFilter;
import com.ggrec.vdf_spring.service.VDFAccountSocialDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@EnableWebSecurity
@Configuration
@Order(1)
public class VDFConfig extends WebSecurityConfigurerAdapter {

    private SocialAuthenticationSuccessHandler socialAuthenticationSuccessHandler;

    private VDFAuthenticationFilter authenticationFilter;

    private UserIdSource userIdSource;

    private VDFAccountSocialDetailsService accountSocialDetailsService;

    @Autowired
    public VDFConfig(
            SocialAuthenticationSuccessHandler socialAuthenticationSuccessHandler,
            VDFAuthenticationFilter authenticationFilter,
            UserIdSource userIdSource,
            VDFAccountSocialDetailsService accountSocialDetailsService
    ) {
        super(true);
        this.socialAuthenticationSuccessHandler = socialAuthenticationSuccessHandler;
        this.authenticationFilter = authenticationFilter;
        this.userIdSource = userIdSource;
        this.accountSocialDetailsService = accountSocialDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Set a custom successHandler on the SocialAuthenticationFilter
        final SpringSocialConfigurer socialConfigurer = new SpringSocialConfigurer();
        socialConfigurer.addObjectPostProcessor(new ObjectPostProcessor<SocialAuthenticationFilter>() {
            @Override
            public <O extends SocialAuthenticationFilter> O postProcess(O socialAuthenticationFilter) {
                socialAuthenticationFilter.setAuthenticationSuccessHandler(socialAuthenticationSuccessHandler);
                return socialAuthenticationFilter;
            }
        });

        http.csrf().disable();

        http
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl();

        http
                .authorizeRequests()

                //allow anonymous font and template requests
                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/resources/**").permitAll()

                // todo facebook only for now
                //allow anonymous calls to social login
                .antMatchers("/auth/facebook/**").permitAll()

                //allow anonymous GETs to API
                .antMatchers(HttpMethod.GET, "/**").permitAll()

                //defined Admin only API area
                .antMatchers("/admin/**").hasRole("ADMIN")

                //all other request need to be authenticated
                .antMatchers(HttpMethod.GET, "/users/current/details").hasRole("USER")
                .anyRequest().hasRole("USER").and()

                // add custom authentication filter for complete stateless JWT based authentication
                .addFilterBefore(authenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)

                // apply the configuration from the socialConfigurer (adds the SocialAuthenticationFilter)
                .apply(socialConfigurer.userIdSource(userIdSource));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountSocialDetailsService);
    }

    @Override
    protected VDFAccountSocialDetailsService userDetailsService() {
        return accountSocialDetailsService;
    }

}