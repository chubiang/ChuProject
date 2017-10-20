package chubiang.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter(){

//    @Autowired
//    lateinit var dataSource: DataSource

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder){
//        auth.jdbcAuthentication().dataSource()
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("1234")
                .authorities("ROLE_ADMIN","ROLE_USER")
    }

    @Throws(Exception::class)
    fun configureHttp (http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/home").access("hasRole('ROLE_USER')")
                .antMatchers("/login*").anonymous().anyRequest().authenticated()
                    .and()
                        .formLogin().loginPage("/login")
                        .loginProcessingUrl("/login?perform")
                        .defaultSuccessUrl("/home")
                        .failureUrl("/login?fail")
                .usernameParameter("email").passwordParameter("password")
                    .and()
                        .logout().logoutSuccessUrl("/login?logout")
    }
}