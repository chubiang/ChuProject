package chubiang.entities

import java.util.*
import javax.persistence.*

@Entity(name = "Person")
@Table(name = "PERSON")
data class Person (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "person_no")
        private  val personNo: Int? = null, // 유저고유번호
        private  val username: String , // 닉네임
        @Column(name = "person_age")
        private  val personAge: Int, // 유저나이
        private  val email: String , // 유저이메일
        private  val password: String, // 유저비밀번호
        @Column(name = "person_img")
        private  val personImg: String, // 유저프로필이미지
        @Column(name = "pet_no")
        private  val petNo: Int, // 펫고유번호
        private  val etc: String, // 유저소개
        @Column(name = "make_date")
        private  val makeDate: Date, // 유저생성날짜
        @Column(name = "update_date")
        private  val updateDate: Date // 유저수정날짜
)