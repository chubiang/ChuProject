package chubiang.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class AppController {

    private val logger = LoggerFactory.getLogger(AppController::class.java)

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
    @RequestMapping(value = "/home", method = arrayOf(RequestMethod.GET))
    fun home(): ModelAndView {
        val mv = ModelAndView()
        mv.viewName = "petCityMain"
        return mv
    }
    @RequestMapping(value = "login/{type}")
    fun login(@PathVariable(required = false) type: String): ModelAndView {
        val mv = ModelAndView()
        if(type.isNullOrEmpty()) {
            mv.viewName = "login"
        }

        return mv
    }

}