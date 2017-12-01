package chubiang.repositories

import chubiang.model.Person
import jooq.model.tables.pojos.Person as jooqPerson
import jooq.model.tables.Person.PERSON
import org.jooq.Record
import org.jooq.Result
import org.jooq.impl.DSL.field
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional(readOnly = true)
class PersonRepository {
    val create = GlobalRepository().dslContext

    fun findPersonEmail(email: String): Any? {
        return create.select()
                .from(PERSON)
                .where(field("EMAIL").eq(email))
                .fetchOne(field("EMAIL"))
    }

    fun findPersonForLogin(person: Person): MutableList<jooqPerson>? {
        return create.select()
                .from(PERSON)
                .where(field("EMAIL").eq(person.email))
                .and(field("PASSWORD").eq(person.password))
                .fetchInto(jooqPerson::class.java)
    }
}