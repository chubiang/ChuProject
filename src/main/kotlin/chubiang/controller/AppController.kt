package chubiang.controller

import chubiang.model.Person
import chubiang.model.ReturnClass
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class AppController {

    private val logger = LoggerFactory.getLogger(AppController::class.java)

    //jsp 페이지
    @RequestMapping(value = ["/"], method = [RequestMethod.GET])
    fun index(): ModelAndView {
        logger.debug("인터셉터 테스트")
        return ModelAndView("index")
    }

    //freemarker 페이지
    @RequestMapping(value = ["/fm"], method = [RequestMethod.GET])
    fun fmIndex(): ModelAndView {
        return ModelAndView("fmIndex")
    }

    //error 403
    @RequestMapping(value = ["/error403"], method = [RequestMethod.GET])
    fun error403(): ModelAndView {
        return ModelAndView("error403")
    }

    @RequestMapping(value = ["/home"], method = [RequestMethod.GET])
    fun home(@ModelAttribute person: Person): ModelAndView {
        val mv = ModelAndView()
        mv.addObject("returnClass", ReturnClass("home", null))
        mv.viewName = "petCityMain"
        return mv
    }

    @RequestMapping(value = ["/login"], method = [RequestMethod.GET])
    fun login(@ModelAttribute person: Person): ModelAndView {
        val mv = ModelAndView()
        mv.addObject("returnClass", ReturnClass("login", null))
        mv.viewName = "petCityMain"
        return mv
    }

    @RequestMapping(value = ["/logout"], method = [RequestMethod.GET])
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        SecurityContextLogoutHandler().logout(request, response, auth)

        return "redirect:/login"
    }

    @RequestMapping(value = ["/signin"], method = [RequestMethod.POST])
    fun loginProcess(@ModelAttribute person: Person, @RequestParam type: String) {
//        loginService.loginPage(type,person)
    }

}