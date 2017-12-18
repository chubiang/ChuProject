package chubiang.service

import jooq.model.tables.pojos.Person as DP
import chubiang.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


class CustomUserDetails(val person: DP) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return person.
    }

    override fun isEnabled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsername(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPassword(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
    override fun loadUserByUsername(email: String?): CustomUserDetails {
        val person = personRepository.findPersonEmail(email!!)
        val authority: GrantedAuthority = SimpleGrantedAuthority(personRepository.findPersonRole(person!!.email!!)!!.role)

        return CustomUserDetails(person)
    }



}