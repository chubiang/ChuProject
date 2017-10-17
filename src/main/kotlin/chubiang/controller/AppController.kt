package chubiang.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
class AppController {

    private val logger = LoggerFactory.getLogger(AppController::class.java)

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun index(): ModelAndView {
        logger.debug("인터셉터 테스트")
        return ModelAndView("index")
    }

}