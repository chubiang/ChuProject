package chubiang.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.servlet.config.annotation.*
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver


@EnableWebMvc
@Configuration
@ComponentScan( basePackages =  arrayOf("chubiang") )
class DispatcherConfig : WebMvcConfigurerAdapter() {

    @Autowired
    lateinit var loggerInterceptor:LoggerInterceptor

    override fun addInterceptors(registry: InterceptorRegistry?) {
        registry!!.addInterceptor(loggerInterceptor)
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("/resources/**").addResourceLocations("/resources/")
    }

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer?) {
        configurer!!.enable()
    }

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer?) {
        configurer!!.ignoreUnknownPathExtensions(false).defaultContentType(MediaType.TEXT_HTML)
    }

    @Bean
    fun viewResolver(): InternalResourceViewResolver {
        val resolver = InternalResourceViewResolver()
        resolver.setViewClass(JstlView::class.java)
        resolver.setPrefix("/WEB-INF/views/")
        resolver.setSuffix(".jsp")
        resolver.order = 1
        return resolver
    }

    @Bean
    fun freemarkerViewResolver(): FreeMarkerViewResolver {
        val resolver = FreeMarkerViewResolver()
        resolver.setPrefix("")
        resolver.setSuffix(".ftl")
        resolver.setExposeSpringMacroHelpers(true)
        resolver.order = 0
        return resolver
    }

    @Bean
    fun freemarkerConfig(): FreeMarkerConfigurer {
        val freeMarkerConfigurer = FreeMarkerConfigurer()
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/ftl")
        return freeMarkerConfigurer
    }


}