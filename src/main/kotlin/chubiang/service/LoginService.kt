package chubiang.service

import chubiang.model.Person
import chubiang.model.ReturnClass
import org.springframework.stereotype.Service

@Service
class LoginService {
    fun loginPage(type: String, person: Person): String{
        var pageName: String = "login"
            if(type == "perform") {
                return "home"
            // person DB에서 select값 리턴
        }
        return "login"
    }
}