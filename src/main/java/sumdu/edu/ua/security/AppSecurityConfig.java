package sumdu.edu.ua.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
public class AppSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/registration", "/signup", "/view/**", "/StudentAdd", "/UserContent").authenticated()  // Доступ для авторизованих користувачів
                        .requestMatchers("/updateScore", "/deleteScore").hasRole("ADMIN") // Доступ тільки для користувачів з роллю ROLE_ADMIN
                        .requestMatchers("/login").permitAll()  // Доступ до сторінки логіну для всіх
                )
                .formLogin(Customizer.withDefaults())  // Використовуємо стандартну сторінку логіну
                .httpBasic(Customizer.withDefaults());  // Доступ через HTTP Basic, якщо потрібно
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }
}
