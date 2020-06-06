package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SuppressWarnings("deprecation")
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ModelMapper initMapper() {
		return new ModelMapper();
	}

	@Bean
	public ITemplateResolver initTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/views/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine intiTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(initTemplateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	@Bean
	@Description("Init ViewResolver")
	public ViewResolver initViewResolver() {
		ThymeleafViewResolver result = new ThymeleafViewResolver();
		result.setCharacterEncoding("UTF-8");
		result.setTemplateEngine(intiTemplateEngine());
		return result;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("classpath:/static/bower_components/jquery/dist/")
				.addResourceLocations("/bower_components/jquery/dist/**");
		registry.addResourceHandler("classpath:/static/bower_components/bootstrap/dist/css/")
				.addResourceLocations("/bower_components/bootstrap/dist/css/**");
		registry.addResourceHandler("classpath:/static/bower_components/Ionicons/css/")
				.addResourceLocations("/bower_components/Ionicons/css/**");
		registry.addResourceHandler("classpath:/static/dist/css/").addResourceLocations("/dist/css/**");
	}

}
