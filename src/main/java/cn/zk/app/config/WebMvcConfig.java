package cn.zk.app.config;

import cn.zk.app.intercepter.SessionIntercepter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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

    private final SessionIntercepter sessionIntercepter;

    @Autowired
    public WebMvcConfig(SessionIntercepter sessionIntercepter) {
        this.sessionIntercepter = sessionIntercepter;
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addRedirectViewController("/", "index");
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
        registry.addInterceptor(sessionIntercepter).addPathPatterns("/**");
    }
}
