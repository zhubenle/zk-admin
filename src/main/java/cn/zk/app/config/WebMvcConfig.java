package cn.consult.admin.app.config;

import cn.consult.admin.app.intercepter.PageIntercepter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

import java.util.List;

/**
 * <br/>
 * Created on 2018/5/9 22:39.
 *
 * @author zhubenle
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

    private final PageIntercepter pageIntercepter;

    @Autowired
    public WebMvcConfig(PageIntercepter pageIntercepter) {
        this.pageIntercepter = pageIntercepter;
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addRedirectViewController("/", "index");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(60 * 60 * 24 * 365)
                .resourceChain(false)
                .addResolver(new GzipResourceResolver())
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(60 * 60 * 24 * 365)
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver());
    }

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.configureHandlerExceptionResolvers(exceptionResolvers);
        exceptionResolvers.add((request, response, handler, ex) -> {
            LOGGER.warn("全局异常处理, 异常信息: ", ex);
            return new ModelAndView("error/500");
        });
    }



    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(pageIntercepter).addPathPatterns("/*s");
    }
}
