package chubiang.service

import chubiang.model.Person
import jooq.model.tables.pojos.Person as DP
import chubiang.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Component
class CustomUserDetails(val person: Person) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return arrayListOf(SimpleGrantedAuthority(person.role))
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return person.username!!
    }

    override fun getPassword(): String {
        return person.password!!
    }

    /*
    http://zgundam.tistory.com/49 자세한 내용 적혀있음
     */
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

}

@Service
@Transactional(readOnly = true)
class CustomUserDetailsService: UserDetailsService{

    @set:Autowired(required = false)
    lateinit var personRepository: PersonRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): CustomUserDetails {
        val person = personRepository.findUserRoleByEmail(email)
        val authority: GrantedAuthority = SimpleGrantedAuthority(person.role)
        println(" ###### GrantedAuthority ------- $authority ")

        return CustomUserDetails(person)
    }



}