package com.example.training.Config;


//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    LoginSuccessHandler successHandler;
//
//    @Autowired
//    LoginFailureHandler failureHandler;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .formLogin()
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .build();
//    }
//
//    /**
//     * 使用 Spring Security 自带的密码加密器
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

import com.example.training.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JWTAuthFilter jwtAuthFilter;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//       httpSecurity.csrf(AbstractHttpConfigurer::disable)
//
//               .authorizeHttpRequests(request->request.requestMatchers("/auth/**","/public/**").permitAll()
//
//                       .requestMatchers("/admin/**").hasAnyAuthority("admin")
//                       .requestMatchers("/user/**").hasAnyAuthority("user")
//                       .requestMatchers("/adminuser/**").hasAnyAuthority("admin","user")
//                       .anyRequest().authenticated()
//               )
//               .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//               .authenticationProvider(authenticationProvider()).addFilterBefore(
//                          jwtAuthFilter, UsernamePasswordAuthenticationFilter.class
//                );
//         return httpSecurity.build();
//
//    }




@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .authorizeRequests()
            .anyRequest().permitAll()
            .and()
            .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


    return httpSecurity.build();
}



//        @Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http.csrf(AbstractHttpConfigurer::disable)
//                    .cors(AbstractHttpConfigurer::disable)
//                    .authorizeHttpRequests(req -> req.requestMatchers("api")
//                            .permitAll()
//                            .anyRequest()
//                            .authenticated())
//                    .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler -> unauthorizedHandler.response.sendError(401, "Unauthorized"))
//                            .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .authenticationProvider(authenticationProvider())
//                    .addFilterBefore(
//                            authenticationJwtTokenFilter(),
//                            UsernamePasswordAuthenticationFilter.class
//                    );
//
//            return http.build();
//        }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}