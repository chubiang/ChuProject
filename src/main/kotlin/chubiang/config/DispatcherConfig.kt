package chubiang.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.web.access.AccessDeniedHandler
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
    lateinit var appInterceptor: AppInterceptor

    override fun addInterceptors(registry: InterceptorRegistry?) {
        registry!!.addInterceptor(appInterceptor)
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

    // Spring security 사용자정의 AccessDeniedHandler handler Bean 등록
    @Bean
    fun accessDeniedHandler(): AccessDeniedHandler {
        return CustomAccessDeniedHandler()
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
        resolver.setContentType("text/html;charset=UTF-8")
        resolver.order = 0
        return resolver
    }

    @Bean
    fun freemarkerConfig(): FreeMarkerConfigurer {
        val freeMarkerConfigurer = FreeMarkerConfigurer()
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/views/ftl")
        freeMarkerConfigurer.setDefaultEncoding("UTF-8")
        return freeMarkerConfigurer
    }


}