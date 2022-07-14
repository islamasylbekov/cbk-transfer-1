package kg.cbk.configuration.security;

import com.google.common.collect.ImmutableList;
import kg.cbk.service.security.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AppUserDetailsService appUserDetailsService;

    public SecurityConfiguration(
            PasswordEncoder passwordEncoder,
            AppUserDetailsService appUserDetailsService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {

        http.authorizeRequests()
                .antMatchers(
                        "/auth/**",
                        "/assets/**",
                        "/favicon.ico"
                )
                .permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/auth/login")
                .defaultSuccessUrl("/", false)
                .failureHandler(authenticationFailureHandler())
                .permitAll();


        http.logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login")
                .permitAll();

        http.csrf().disable();

        http
                .rememberMe()
                .tokenValiditySeconds(864000)
                .key("vht-app");

        http.cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            if (exception instanceof CredentialsExpiredException) {
                response.sendRedirect(String.format("/auth/update-password?username=%s", request.getParameter("username")));
            } else {
                response.sendRedirect("/auth/login?error=true");
            }
        };
    }

}