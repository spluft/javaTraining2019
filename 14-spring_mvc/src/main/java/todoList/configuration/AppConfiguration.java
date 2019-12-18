package todoList.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"todoList"})
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/src/main/webapp/WEB-INF/css/**").addResourceLocations("/src/main/webapp/WEB-INF/css/");
    }

    @Bean
    public ResourceBundleThemeSource themeSource(){
        ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
        themeSource.setDefaultEncoding("UTF-8");
        themeSource.setBasenamePrefix("themes.");
        return themeSource;
    }

    @Bean
    public CookieThemeResolver themeResolver(){
        CookieThemeResolver resolver = new CookieThemeResolver();
        resolver.setCookieName("theme");
        resolver.setDefaultThemeName("first");
        return resolver;
    }

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
//        return new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor1 = new LocaleChangeInterceptor();
        interceptor1.setParamName("lang");
        ThemeChangeInterceptor interceptor2 = new ThemeChangeInterceptor();
        interceptor2.setParamName("theme");
        registry.addInterceptor(interceptor1);
        registry.addInterceptor(interceptor2);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("ru"));
        resolver.setCookieName("myLocale");
        resolver.setCookieMaxAge(3600);
        return resolver;
    }

    @Bean
    public DelegatingFilterProxy getDelegatingFilterProxy() {
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        return proxy;
    }

}
