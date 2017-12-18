package chubiang.repositories

import chubiang.model.Person
import chubiang.model.Role
import jooq.model.tables.pojos.Person as jooqPerson
import jooq.model.tables.Person.PERSON
import jooq.model.tables.UserRoles.USER_ROLES
import org.jooq.impl.DSL.field
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PersonRepository {
    val create = GlobalRepository().dslContext

    @Transactional(readOnly = true)
    fun findPersonRole(email: String): Role? {
        return create.select()
                .from(USER_ROLES)
                .where(field("EMAIL").eq(email))
                .fetchOne().into(Role::class.java)
    }

    @Transactional(readOnly = true)
    fun findPersonEmail(email: String): jooqPerson? {
        return create.select()
                .from(PERSON)
                .where(field("EMAIL").eq(email))
                .fetchOne().into(jooqPerson::class.java)
    }

    @Transactional(readOnly = true)
    fun findPersonForLogin(person: Person): MutableList<jooqPerson>? {
        return create.select()
                .from(PERSON)
                .where(field("EMAIL").eq(person.email))
                .and(field("PASSWORD").eq(person.password))
                .fetchInto(jooqPerson::class.java)
    }
}