package tqs.project.movie_goodies.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //[[${#httpServletRequest.remoteUser}]]
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/shop").setViewName("shop");
        registry.addViewController("/item").setViewName("item");
        registry.addViewController("/user/item").setViewName("item");
        registry.addViewController("/user/login").setViewName("login");
        registry.addViewController("/user/logout").setViewName("login");
        registry.addViewController("/user/register").setViewName("register");
        registry.addViewController("/user/cart").setViewName("cart");
        registry.addViewController("/user/profile").setViewName("profile");
        registry.addViewController("/user/checkout").setViewName("checkout");
        registry.addViewController("/provider/login").setViewName("login_provider");
        registry.addViewController("/provider/logout").setViewName("login_provider");
        registry.addViewController("/provider/register").setViewName("register_provider");
        registry.addViewController("/provider/products").setViewName("products");
        registry.addViewController("/provider/insert").setViewName("insert");
        registry.addViewController("/provider/profile").setViewName("profile_provider");
        registry.addViewController("/provider/sales").setViewName("sales");
        registry.addViewController("/provider/orders").setViewName("orders");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/images/**",
                "/css/**",
                "/js/**",
                "/fonts/**",
                "/includes/**",
                "/vendor/**")
                .addResourceLocations(
                        "classpath:/static/images/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/fonts/",
                        "classpath:/static/includes/",
                        "classpath:/static/vendor/");
    }

}