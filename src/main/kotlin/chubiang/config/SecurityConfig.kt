package chubiang.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
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
        auth.jdbcAuthentication().dataSource()
    }
}