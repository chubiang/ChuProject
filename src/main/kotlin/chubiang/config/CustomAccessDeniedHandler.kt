package chubiang.config

import org.apache.juli.logging.LogFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAccessDeniedHandler: AccessDeniedHandler {

    private var log = LogFactory.getLog(CustomAccessDeniedHandler::class.java)

    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        var auth: Authentication = SecurityContextHolder.getContext().authentication
        if(auth != null) {
            log.warn("""Login User = ${auth.name} attempted to access the protected URL: ${request!!.requestURI}""")
        }

        response!!.sendRedirect(request!!.contextPath+"/error403")
    }

}