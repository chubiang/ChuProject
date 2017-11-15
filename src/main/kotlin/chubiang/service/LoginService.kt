package chubiang.service

import chubiang.entities.Person
import chubiang.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginService {

    @Autowired
    lateinit var personRepository: PersonRepository

    fun loginPage(type: String, person: Person): String{
        var pageName: String = "login"
            if(type == "perform") {
                //테이블에서 데이터 가져와서 비밀번호 비교

//                personRepository.getByEmail()
                return "home"
        }
        return "login"
    }
}