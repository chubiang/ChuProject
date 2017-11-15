package chubiang.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity(name = "person")
@Table(name = "PERSON")
data class Person (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "personNo")
        private  val personNo: Int? = null, // 유저고유번호
        private  val username: String , // 닉네임
        @Column(name = "personAge")
        private  val personAge: Int, // 유저나이
        private  val email: String , // 유저이메일
        @JsonIgnore
        private  val password: String, // 유저비밀번호
        @Column(name = "personImg")
        private  val personImg: String? = "", // 유저프로필이미지
        @Column(name = "petNo")
        private  val petNo: Int? = null, // 펫고유번호
        private  val etc: String? = "", // 유저소개
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "makeDate")
        private  val makeDate: Date? = null, // 유저생성날짜
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updateDate")
        private  val updateDate: Date? = null // 유저수정날짜
)