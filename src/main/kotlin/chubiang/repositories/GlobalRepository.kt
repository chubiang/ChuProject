package chubiang.repositories

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager

class GlobalRepository {
    private val conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/chuproject",
            "nana",
            "nana"
    )
    val dslContext: DSLContext = DSL.using(conn, SQLDialect.POSTGRES)
}