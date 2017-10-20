package chubiang.config

import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import javax.servlet.Filter


class ApplicationInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getServletConfigClasses(): Array<Class<*>> {
        return arrayOf(DispatcherConfig::class.java)
    }

    override fun getServletMappings(): Array<String> {
        return arrayOf("/")
    }

    override fun getServletFilters(): Array<Filter> {
        val encodingFilter = CharacterEncodingFilter()
        encodingFilter.encoding = "UTF-8"
        encodingFilter.setForceEncoding(true)
        return arrayOf(encodingFilter)
    }

    override fun getRootConfigClasses(): Array<Class<*>>? {
        return arrayOf(RepositoryConfig::class.java, SecurityConfig::class.java)
    }
}