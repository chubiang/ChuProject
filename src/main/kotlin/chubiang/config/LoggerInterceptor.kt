package chubiang.config

import org.apache.juli.logging.LogFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoggerInterceptor: HandlerInterceptorAdapter() {
    protected  var log = LogFactory.getLog(LoggerInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
        if (log.isDebugEnabled) {
            log.debug("======================================          START         ======================================")
            log.debug(" Request URI \t:  " + request!!.getRequestURI())
        }

        return super.preHandle(request, response, handler)
    }

    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
        if (log.isDebugEnabled) {
            log.debug("======================================           END          ======================================\n")
        }

        super.postHandle(request, response, handler, modelAndView)
    }
}