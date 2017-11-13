package chubiang.repositories

import chubiang.entities.Person
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@Transactional
class PersonRepository {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    fun create(person: Person) {
        entityManager.persist(person)
    }

    fun getByEmail(email: String): Any? {
        return entityManager.createNativeQuery("SELECT * FROM Person WHERE email = :email")
                .setParameter("email", email)
                .singleResult
    }
}