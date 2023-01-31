package HotDeal.HotDeal.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final JwtAuthInterceptor jwtAuthInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthInterceptor)
                .addPathPatterns("/**");
//                .addPathPatterns("/topics")
//                .addPathPatterns("/topics/**")
//                .addPathPatterns("/private/topics/**")
//                .addPathPatterns("/profile/**")
//                .addPathPatterns("/leaderboard/*")
//                .addPathPatterns("/ongoing/**")
//                .addPathPatterns("/bonus/**")
//                .addPathPatterns("/comments")
//                .addPathPatterns("/comments/**")
//                .addPathPatterns("/admin/**")
//                .addPathPatterns("/tags/*");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}