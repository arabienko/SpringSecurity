package by.arabienko.spring.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Tatsiana")
                .password("123")
                .roles("employee")
                .build();
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("Zaur")
                .password("321")
                .roles("customer","hr")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("Kondar")
                .password("456")
                .roles("hr")
                .build();
        return new InMemoryUserDetailsManager(user,user1,user2);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/").hasAnyRole("customer","hr", "employee")
                        .antMatchers("/hr_info").hasAnyRole("hr")
                .antMatchers("/customer_info/**").hasAnyRole("customer","employee")
                .and().formLogin().permitAll();

//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
        return http.build();
    }
}
