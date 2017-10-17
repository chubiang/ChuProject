package chubiang.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.*
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView


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

    @Bean
    fun viewResolver(): InternalResourceViewResolver {
        val resolver = InternalResourceViewResolver()
        resolver.setViewClass(JstlView::class.java)
        resolver.setPrefix("/WEB-INF/views/")
        resolver.setSuffix(".jsp")
        return resolver
    }

  /*  @Bean
    fun freemarkerViewResolver(): FreeMarkerViewResolver {
        var resolver = FreeMarkerViewResolver()
        resolver.setPrefix("")
        resolver.setSuffix(".ftl")
        return resolver
    }

    @Bean
    fun freemarkerConfig(): FreeMarkerConfigurer {
        var freeMarkerConfigurer = FreeMarkerConfigurer()
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/view/ftl/")
        return freeMarkerConfigurer
    }*/


}