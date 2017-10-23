package chubiang.service

import chubiang.model.Person
import chubiang.model.ReturnClass
import org.springframework.stereotype.Service

@Service
class LoginService {
    fun loginPage(type: String, person: Person): ReturnClass{
        var pageName: String = "login"
        if(type == "perform") {
            pageName = "home"
            // person DB에서 select값 리턴
        }
        return ReturnClass(pageName, person)
    }
}