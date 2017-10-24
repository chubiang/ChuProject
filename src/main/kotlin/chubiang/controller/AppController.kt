package chubiang.controller

import chubiang.model.Person
import chubiang.model.ReturnClass
import chubiang.service.LoginService
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateException
import freemarker.template.TemplateExceptionHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.util.HashMap





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
    fun home(): ModelAndView {
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

    @RequestMapping(value = "/login/{type}")
    fun loginProcess(@PathVariable type: String, @ModelAttribute person: Person): ModelAndView {
        val mv = ModelAndView()
        var data_model: MutableMap<String, Any> = mutableMapOf()
        var returnClass: ReturnClass = loginService.loginPage(type, person)
        mv.addObject("returnClass", returnClass)

        mv.viewName = "petCityMain"
        return mv
    }

}