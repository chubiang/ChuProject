package chubiang.mvc

import org.apache.catalina.core.StandardContext
import org.apache.catalina.startup.Tomcat
import java.io.File

class Application {
    fun main(args: Array<String>) {
        var webAppDirLocation = "src/main/"
        var tomcat = Tomcat()

        //Set Port
        tomcat.setPort(8080)

        var ctx: StandardContext = tomcat.addWebapp("/", File(webAppDirLocation).absolutePath) as StandardContext

        tomcat.start()
        tomcat.server.await()
    }
}

