package chubiang.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = arrayOf(elements = "chubiang.repositories"))
@PropertySource("classpath:jpa.properties")
class RepositoryConfig {
    /**
     * Jpa + Hibernate 5
     */

    @Autowired
    lateinit private var env: Environment

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url ="jdbc:postgresql://localhost:5432/chuproject"
        dataSource.username = "nana"
        dataSource.password = "nana"
        return dataSource
    }

    @Bean
    fun entityManagerFactory(): EntityManagerFactory {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = vendorAdapter
        factory.setPackagesToScan("chubiang.entities")
        factory.dataSource = dataSource()
        factory.setJpaProperties(jpaProperties())
        factory.afterPropertiesSet()
        return factory.`object`
    }

    private fun jpaProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"))
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"))
        properties.setProperty("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"))
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"))
        return properties
    }

    @Bean
    fun transactionManager(): JpaTransactionManager {
        val txManager = JpaTransactionManager()
        txManager.entityManagerFactory = entityManagerFactory()
        return txManager
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then rethrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     */
    @Bean
    fun exceptionTranslation() = PersistenceExceptionTranslationPostProcessor()


}