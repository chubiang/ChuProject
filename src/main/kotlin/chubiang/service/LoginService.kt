package chubiang.service

import chubiang.model.Person
import jooq.model.tables.pojos.Person as jooqPerson
import chubiang.repositories.PersonRepository
import org.jooq.Record
import org.jooq.Result
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginService {

//    @Autowired
//    lateinit var jpaPersonRepository: JpaPersonRepository
    @Autowired
    lateinit var personRepository: PersonRepository

    fun loginPage(type: String, person: Person): String{
        var pageName: String = "login"
            if(type == "perform") {
                //테이블에서 데이터 가져와서 비밀번호 비교
                var personList: List<jooqPerson>? = personRepository.findPersonForLogin(person)
                if(personList?.size != 0) {
                    return "home"
                }
        }
        return "login"
    }
}