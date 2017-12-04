package chubiang.config

import chubiang.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder


//특정 configuration 파일의 빈을 autowired 시키고 싶을 땐 import를 꼭해줘야 initializetion 문제가 안생김
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
@ComponentScan(basePackages = arrayOf("chubiang.service"))
@Import(CustomUserDetailsService::class)
class SecurityConfig: WebSecurityConfigurerAdapter(){


    @Autowired
    private lateinit var customUserDetailsService: UserDetailsService

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(customUserDetailsService)
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

}