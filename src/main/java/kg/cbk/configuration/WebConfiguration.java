package kg.cbk.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.concurrent.TimeUnit;

@EnableWebMvc
@Configuration
@EnableRedisHttpSession
public class WebConfiguration implements WebMvcConfigurer {

    private final LocaleChangeInterceptor localeChangeInterceptor;

    @Autowired
    public WebConfiguration(
            LocaleChangeInterceptor localeChangeInterceptor
    ) {
        this.localeChangeInterceptor = localeChangeInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/assets/favicon.ico")
                .setCacheControl(CacheControl.maxAge(12, TimeUnit.HOURS))
                .resourceChain(true);
        registry
                .addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/")
                .setCacheControl(CacheControl.maxAge(12, TimeUnit.HOURS))
                .resourceChain(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");
    }

}