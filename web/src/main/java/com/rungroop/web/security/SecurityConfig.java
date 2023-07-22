package com.rungroop.web.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/login","/register","/clubs","/css/**","/js/**") // this url(s) will not be block by the Spring security
                    .permitAll()
                    .and()
                    .formLogin(form -> form    // Login config start from here
                            .loginPage("/login")  // url of login page
                            .defaultSuccessUrl("/clubs") // when the login success web will be redirect to /clubs
                            .loginProcessingUrl("/login") // where the login will be processed (this will hanlde the post method.)

                            .failureUrl("/login?error=true") // this will handle the error.
                            .permitAll()   // Login config ends here
                    ).logout( logout -> logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll() // path of the logout
                    );
            return http.build();
        }

        public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        }

}
