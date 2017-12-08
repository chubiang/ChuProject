package chubiang.config

import com.zaxxer.hikari.HikariDataSource
import org.jooq.ExecuteContext
import org.jooq.SQLDialect
import org.jooq.impl.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

open class ExceptionTranslator : DefaultExecuteListener() {
    override fun exception(context: ExecuteContext?) {
        val dialect = context!!.configuration().dialect()
        val translator = SQLErrorCodeSQLExceptionTranslator(dialect.name)
        context.exception(translator
                .translate("jOOQ ", context.sql(), context.sqlException()))
    }
}

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
class PersistenceConfig {

    fun dataSource(): HikariDataSource {
        val ds = HikariDataSource()
        ds.maximumPoolSize = 100
        ds.driverClassName = "org.postgresql.Driver"
        ds.jdbcUrl = "jdbc:postgresql://localhost:5432/chuproject"
        ds.username = "nana"
        ds.password = "nana"
        return ds
    }

    @Bean
    fun transactionAwareDataSource(): TransactionAwareDataSourceProxy {
        return TransactionAwareDataSourceProxy(dataSource())
    }

    @Bean
    fun transactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }

    @Bean
    fun connectionProvider(): DataSourceConnectionProvider {
        return DataSourceConnectionProvider(transactionAwareDataSource())
    }

    @Bean
    fun exceptionTransformer(): ExceptionTranslator {
        return ExceptionTranslator()
    }

    @Bean
    fun dsl(): DefaultDSLContext {
        return DefaultDSLContext(configuration())
    }

    @Bean
    fun configuration(): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(connectionProvider())
        jooqConfiguration.set(DefaultExecuteListenerProvider(exceptionTransformer()))

        val sqlDialectName = "POSTGRES"
        val dialect = SQLDialect.valueOf(sqlDialectName)
        jooqConfiguration.set(dialect)

        return jooqConfiguration
    }
}