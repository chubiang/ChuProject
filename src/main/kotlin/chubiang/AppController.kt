package chubiang

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class AppController {

    @GetMapping("/")
    fun index(): ModelAndView =  ModelAndView("index")

}