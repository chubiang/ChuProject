package chubiang.model

import org.springframework.stereotype.Component
import java.util.*

/**-------------------------------------
    Person.class
----------------------------------------
    personNo (pk) / 유저고유번호
    Username / 닉네임
    personAge / 유저나이
    Email / 유저이메일
    Password / 유저비밀번호
    personImg / 유저프로필이미지
    petNo (fk) / 펫고유번호
    Etc / 유저소개
    makeDate / 유저생성날짜
    updateDate / 유저수정날짜
 */
@Component
data class Person(val personNo: String, val username: String, val personAge: Int, val email: String,
                  val password: String, val personImg: String, val petNo: String, val etc: String,
                  val makeDate: Date, val updateDate: Date)