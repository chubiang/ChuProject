package chubiang.config

import org.apache.commons.logging.LogFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * http://zgundam.tistory.com/52
 * http://www.baeldung.com/spring_redirect_after_login
 */
class CustomAuthenticationSuccessHandler: AuthenticationSuccessHandler {

    private val log = LogFactory.getLog(CustomAuthenticationSuccessHandler::class.java)
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()

    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        handle(request, response, authentication)
        
    }

    protected fun handle(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        val targetUrl = determineTargetUrl(authentication)
        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    protected fun determineTargetUrl(authentication: Authentication?): String {
        val isUser = false
        val isAdmin = false

        val authorities = authentication!!.authorities
        authorities.forEach { x ->
            print(x.authority)
        }
        return "/home"
    }
    protected fun clearAuthenticationAttribute(request: HttpServletRequest?) {
        val session: HttpSession = request!!.getSession(false)
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
    }

}