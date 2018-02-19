package chubiang.handler

import org.apache.juli.logging.LogFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler: AccessDeniedHandler {

    private val log = LogFactory.getLog(CustomAccessDeniedHandler::class.java)

    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {

        // Ajax요청인지 확인을 위한 헤더값 추출
//        val ajaxHeader = request!!.getHeader("x-Ajax-call")

        var auth: Authentication = SecurityContextHolder.getContext().authentication
        if(auth != null) {
            log.debug("""Login User = ${auth.name} attempted to access the protected URL: ${request!!.requestURI}""")
        }
        log.debug("error URL ==== "+request!!.contextPath+"/error403")
        response!!.sendRedirect(request!!.contextPath+"/error403")
    }

}