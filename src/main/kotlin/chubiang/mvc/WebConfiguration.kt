package chubiang.mvc

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer


class WebConfiguration : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getServletConfigClasses(): Array<Class<*>> {
        return arrayOf(ApplicationWebConfig::class.java)
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    override fun getRootConfigClasses(): Array<Class<*>>? {
        return null
    }

}