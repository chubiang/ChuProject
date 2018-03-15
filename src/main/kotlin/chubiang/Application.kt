package chubiang

import org.apache.catalina.startup.Tomcat
import java.io.File
import java.io.IOException

class Application {
object Application {

    private val PORT = 8080

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val webAppDir = "src/main/webapp"

        val tomcat = Tomcat()
        tomcat.setBaseDir(createTempDir())
        tomcat.setPort(PORT)
        tomcat.addWebapp("", File(webAppDir).absolutePath)
        tomcat.start()
        tomcat.server.await()
    }

    fun createTempDir(): String {
        try {
            val tempDir = File.createTempFile("tomcat","."+ PORT)
            tempDir.delete()        // 파일 삭제
            tempDir.mkdir()         // 폴더 생성
            tempDir.deleteOnExit()  // 임시파일 생성후 사용종료 시에 삭제시킴
            return tempDir.absolutePath
        } catch ( ex: IOException) {
            throw RuntimeException(
                    "Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"),
                    ex
            )
        }
    }

}
}