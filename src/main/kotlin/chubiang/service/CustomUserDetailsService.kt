package chubiang.service

import jooq.model.tables.pojos.Person as DP
import chubiang.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Component(value = "userDetailsService")
@Service
@Transactional(readOnly = true)
class CustomUserDetailsService: UserDetailsService{

    @set:Autowired(required = false)
    lateinit var personRepository: PersonRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String?): UserDetails {
        val person = personRepository.findPersonEmail(email!!)
        val authority: GrantedAuthority = SimpleGrantedAuthority(personRepository.findPersonRole(person!!.email!!)!!.role)

        return User(person.email, person.password, listOf(authority))
    }



}