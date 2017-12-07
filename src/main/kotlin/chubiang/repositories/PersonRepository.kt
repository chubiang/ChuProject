package chubiang.repositories

import chubiang.model.Person
import chubiang.model.Role
import jooq.model.tables.pojos.Person as jooqPerson
import jooq.model.tables.Person.PERSON
import jooq.model.tables.UserRoles.USER_ROLES
import org.jooq.Record
import org.jooq.Result
import org.jooq.impl.DSL.field
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
//@Transactional(readOnly = true)
class PersonRepository {
    val create = GlobalRepository().dslContext

    fun findPersonRole(email: String): Role? {
        return create.select()
                .from(USER_ROLES)
                .where(field("EMAIL").eq(email))
                .fetchAny().into(Role::class.java)
    }

    fun findPersonEmail(email: String): Person? {
        return create.select()
                .from(PERSON)
                .where(field("EMAIL").eq(email))
                .fetchAny().into(Person::class.java)
    }

    fun findPersonForLogin(person: Person): MutableList<jooqPerson>? {
        return create.select()
                .from(PERSON)
                .where(field("EMAIL").eq(person.email))
                .and(field("PASSWORD").eq(person.password))
                .fetchInto(jooqPerson::class.java)
    }
}