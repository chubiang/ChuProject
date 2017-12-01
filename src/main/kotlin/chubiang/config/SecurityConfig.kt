package chubiang.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter(){

    @Autowired
    @Throws(Exception::class)
    fun configAuthentication(auth: AuthenticationManagerBuilder) {
        auth.jdbcAuthentication().passwordEncoder(passwordEncoder())
    }


    // 글로벌 범위의 spring security 설정
//    @Autowired
//    @Throws(Exception::class)
//    fun configureGlobal(auth: AuthenticationManagerBuilder){
//        auth.inMemoryAuthentication()
//                .withUser("admin@example.co.kr")
//                .password("1234")
//                .authorities("ROLE_ADMIN","ROLE_USER")
//        print("들어왔음")
//    }

    // http 프로토콜 url이 들어올 때 적용시킬 spring security 설정
    @Throws(Exception::class)
    override fun configure (http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/login*").anonymous().anyRequest().authenticated()
                .antMatchers("/home").access("hasRole('ROLE_USER')")
                    .and()
                .formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login")
                .usernameParameter("email").passwordParameter("password")
                    .and()
                .logout()
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                    .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/error403")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}