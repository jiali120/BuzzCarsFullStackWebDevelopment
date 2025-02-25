package gatech.cs.buzzcar.config;

import gatech.cs.buzzcar.common.interceptor.AuthTokenInterceptor;
import gatech.cs.buzzcar.common.interceptor.PermissionInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private AuthTokenInterceptor authTokenInterceptor;

    @Resource
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> requestHolderList = Arrays.asList("/login", "/public/**");
        registry.addInterceptor(authTokenInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/**").excludePathPatterns(requestHolderList);
    }



    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*");

        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");

        config.addAllowedHeader("*");
        config.addExposedHeader("Cookie");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }
}