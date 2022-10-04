package by.arabienko.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    DataSource dataSource;

    //    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("Tatsiana")
//                .password("123")
//                .roles("employee")
//                .build();
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("Zaur")
//                .password("321")
//                .roles("manager","hr")
//                .build();
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("Kondar")
//                .password("456")
//                .roles("hr")
//                .build();
//        return new InMemoryUserDetailsManager(user,user1,user2);
//    }
    /*@Autowired
    public void users(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
        }*/

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/").hasAnyRole("MANAGER", "HR", "EMPLOYEE")
                .antMatchers("/hr_info").hasAnyRole("HR")
                .antMatchers("/customer_info/**").hasAnyRole("MANAGER", "EMPLOYEE")
                .and().formLogin().permitAll();
        return http.build();
    }
}
