    package com.example.Pet.Security;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.*;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    //import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.security.web.*;

    @Configuration
    @EnableWebSecurity
    @EnableMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {
        @Autowired
        private JwtTokenFilter jwtTokenFilter;

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
            return cfg.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/auth/**").permitAll() // tất cả điều nhận được otp
                            .requestMatchers("/api/services").hasAnyRole("ADMIN", "CUSTOMER") // tất cả đều xem được dịch vụ
                            .requestMatchers(HttpMethod.POST, "/api/services/**").hasRole("ADMIN") // quyền được thêm
                            .requestMatchers(HttpMethod.PUT, "/api/services/**").hasRole("ADMIN") // quyền sửa
                            .requestMatchers(HttpMethod.DELETE, "/api/services/**").hasRole("ADMIN") // quyền xóa
                            .requestMatchers(HttpMethod.GET, "/api/appointment/**").hasAnyRole("CUSTOMER", "ADMIN")
                            .requestMatchers(HttpMethod.POST, "/api/appointment/**").hasRole("CUSTOMER")
                            .requestMatchers("/api/admin/login").permitAll()

                            .anyRequest().authenticated()
                    )
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
    }
