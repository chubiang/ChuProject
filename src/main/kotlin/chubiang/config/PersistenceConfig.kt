package chubiang.config

import chuproject.BuildConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.ExecuteContext
import org.jooq.SQLDialect
import org.jooq.impl.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator
import org.springframework.transaction.annotation.EnableTransactionManagement

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
        ds.maximumPoolSize = 10 // 기존 postgres conf파일에 있는 max_connection보다 적게 잡아줘야 안뻗음
        ds.driverClassName = BuildConfig.DRIVER_CLASS_NAME
        ds.jdbcUrl = BuildConfig.JDBC_URL
        ds.username = BuildConfig.JDBC_USERNAME
        ds.password = BuildConfig.JDBC_PASSWORD
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