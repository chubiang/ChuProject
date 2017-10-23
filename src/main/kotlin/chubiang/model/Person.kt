package chubiang.model

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
data class Person(private var personNo: String = "",
                  private var username: String = "",
                  private var personAge: Int = 0,
                  private var email: String  = "",
                  private var password: String  = "",
                  private var personImg: String  = "",
                  private var petNo: String  = "",
                  private var etc: String?  = "",
                  private var makeDate: Date? = null,
                  private var updateDate: Date? = null)