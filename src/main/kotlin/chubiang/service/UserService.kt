package chubiang.service

import chubiang.model.Person
import chubiang.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import jooq.model.tables.pojos.Person as DP
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService {

    @Autowired(required = false)
    lateinit var personRepository: PersonRepository

     fun loginPage(type: String, person: Person): String{
         var pageName: String = "login"
         if(type == "perform") {
             //테이블에서 데이터 가져와서 비밀번호 비교
             var personList: List<DP>? = personRepository.findPersonForLogin(person)
             println("들어옴11111111")
             if(personList?.size != 0) {
                 println("들어옴2222222222")
                 return "home"
             }
         }
         return "login"
    }
}