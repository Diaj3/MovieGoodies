package tqs.project.movie_goodies.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import tqs.project.movie_goodies.services.CustomerService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tqs.project.movie_goodies.services.ProviderService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(1)
    public class ProviderSecurityConfig extends WebSecurityConfigurerAdapter {
        @Bean
        public UserDetailsService providerDetailsService() {
            return new ProviderService();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(providerDetailsService()).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/provider/register").permitAll()
                    .and().antMatcher("/provider/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("PROVIDER")
                    .and().exceptionHandling().accessDeniedPage("/provider/logout")
                    .and().formLogin()
                    .loginPage("/provider/login").defaultSuccessUrl("/provider/products").permitAll()
                    .and().logout().logoutUrl("/provider/logout").permitAll()
                    .and().csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public class ConsumerSecurityConfig extends WebSecurityConfigurerAdapter {

        @Bean
        public UserDetailsService customerDetailsService() {
            return new CustomerService();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(customerDetailsService()).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/user/register").permitAll()
                    .and().antMatcher("/user/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasRole("CUSTOMER")
                    .and().exceptionHandling().accessDeniedPage("/user/logout")
                    .and().formLogin()
                    .loginPage("/user/login").permitAll()
                    .and().logout().logoutUrl("/user/logout").permitAll()
                    .and().csrf().disable();
        }
    }

    @Configuration
    @Order(3)
    public static class MainSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/", "/shop", "/item/**").permitAll()
                    .and().csrf().disable();
        }
    }
}
