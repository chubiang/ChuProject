package chubiang.mvc

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
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
@ComponentScan( "chubiang"  )
class ApplicationWebConfig : WebMvcConfigurerAdapter(), ApplicationContextAware {

    private var applicationContext: ApplicationContext? = null

    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        this.applicationContext = applicationContext
    }

    override fun addViewControllers(registry: ViewControllerRegistry?) {
        super.addViewControllers(registry)

        registry!!.addViewController("/").setViewName("home")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("/resources/**").addResourceLocations("/src/webapp/WEB-INF/views")
    }

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer?) {
        configurer!!.mediaType("json", MediaType.APPLICATION_JSON)
    }

    fun resolver(): InternalResourceViewResolver {
        var resolver: InternalResourceViewResolver = InternalResourceViewResolver()
        resolver.setViewClass(JstlView::class.java)
        resolver.setPrefix("/WEB-INF/views/")
        resolver.setSuffix(".jsp")
        return resolver
    }

    @Bean
    fun freemarkerViewResolver(): FreeMarkerViewResolver {
        var resolver: FreeMarkerViewResolver = FreeMarkerViewResolver()
        resolver.setPrefix("")
        resolver.setSuffix(".ftl")
        return resolver
    }

    @Bean
    fun freemarkerConfig(): FreeMarkerConfigurer {
        var freeMarkerConfigurer: FreeMarkerConfigurer = FreeMarkerConfigurer()
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/view/ftl/")
        return freeMarkerConfigurer
    }


}