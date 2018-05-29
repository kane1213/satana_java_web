package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MvcConfig extends WebMvcConfigurationSupport{
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("hello");
        //registry.addViewController("/productdata").setViewName("productdata");
        //registry.addViewController("/error").setViewName("error");
		//registry.addViewController("/fansplay").setViewName("fansplay");
		//WebMvcConfigurationSupport
    }
	
	/*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**");		//.addResourceLocations("file:/opt/cms/upload/")
        super.addResourceHandlers(registry);
    }*/
}
