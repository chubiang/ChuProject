package chubiang.controller

import chubiang.entities.Person
import chubiang.model.ReturnClass
import chubiang.service.LoginService
import freemarker.template.Configuration
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Controller
class AppController {

    private val logger = LoggerFactory.getLogger(AppController::class.java)
    private val cfg = Configuration(Configuration.VERSION_2_3_25)

    @Autowired
    private lateinit var loginService: LoginService

    //jsp 페이지
    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun index(): ModelAndView {
        logger.debug("인터셉터 테스트")
        return ModelAndView("index")
    }
    //freemarker 페이지
    @RequestMapping(value = "/fm", method = arrayOf(RequestMethod.GET))
    fun fmIndex(): ModelAndView {
        return ModelAndView("fmIndex")
    }
    //error 403
    @RequestMapping(value = "/error403", method = arrayOf(RequestMethod.GET))
    fun error403(): ModelAndView {
        return ModelAndView("error403")
    }

    @RequestMapping(value = "/home", method = arrayOf(RequestMethod.GET))
    fun home(@ModelAttribute person: Person): ModelAndView {
        val mv = ModelAndView()
        mv.addObject("returnClass", ReturnClass("home", null))
        mv.viewName = "petCityMain"
        return mv
    }

    @RequestMapping(value = "/login", method = arrayOf(RequestMethod.GET))
    fun login(@ModelAttribute person: Person): ModelAndView {
        val mv = ModelAndView()
        mv.addObject("returnClass", ReturnClass("login", null))
        mv.viewName = "petCityMain"
        return mv
    }

    @RequestMapping(value = "/logout", method = arrayOf(RequestMethod.GET))
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        var auth: Authentication = SecurityContextHolder.getContext().authentication
        if(auth != null) {
            SecurityContextLogoutHandler().logout(request, response, auth)
        }
        return "redirect:/login"
    }

    @RequestMapping(value = "/signin", method = arrayOf(RequestMethod.POST))
    fun loginProcess(@ModelAttribute person: Person, @RequestParam type: String) {
        loginService.loginPage(type,person)
    }

}