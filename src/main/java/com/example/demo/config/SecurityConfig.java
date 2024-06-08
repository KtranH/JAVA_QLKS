package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll()) // Cho phép tất cả các request
            .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu không cần thiết
            .formLogin(withDefaults -> withDefaults.disable()) // Tắt form đăng nhập
            .httpBasic(withDefaults -> withDefaults.disable()); // Tắt xác thực HTTP Basic
        return http.build();
    }
    //@Bean
    //public SecurityFilterChain securityFilterChain(HttpSecurity http) throws //Exception {
    //    http
    //        .authorizeHttpRequests(authorize -> authorize
    //           .requestMatchers("/login").permitAll() // Cho phép truy cập không cần xác thực vào trang đăng nhập
    //            .anyRequest().authenticated() // Yêu cầu xác thực cho các request khác
    //        )
    //        .formLogin(formLogin -> formLogin
    //            .loginPage("/login") // Trang đăng nhập tùy chỉnh
    //            .defaultSuccessUrl("/home", true) // Chuyển hướng đến /home sau khi đăng nhập thành công
    //           .permitAll()
    //        )
    //        .logout(logout -> logout
    //           .logoutUrl("/logout")
    //            .logoutSuccessUrl("/login")
    //            .invalidateHttpSession(true)
    //            .deleteCookies("JSESSIONID")
    //        )
    //        .csrf(csrf -> csrf.disable()); // Tắt CSRF nếu không cần thiết
    //    return http.build();
    //}
}
