import chubiang.config.DispatcherConfig
import chubiang.config.PersistenceConfig
import chubiang.config.SecurityConfig
import chubiang.repositories.PersonRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.AnnotationConfigWebContextLoader
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@ContextConfiguration(
        classes = [(DispatcherConfig::class), (SecurityConfig::class), (PersistenceConfig::class)],
        loader = AnnotationConfigWebContextLoader::class
)
class  SourceTest {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Rollback
    @Test
    fun test() {
        /**
         * SQL Statemant Test
         */

    }
}