// package com.example.chat.application.config;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
// import lombok.extern.slf4j.Slf4j;
//
// @Configuration
// @EnableWebMvc
// @Slf4j
// public class WebConfig implements WebMvcConfigurer {
//
// 	@Autowired
// 	private ApplicationContext applicationContext;
//
// 	// @Override
// 	// public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
// 	// 	configurer.enable();
// 	//
// 	// }
//
// 	@Override
// 	public void addResourceHandlers(ResourceHandlerRegistry registry) {
// 		registry.addResourceHandler("/**")
// 			.addResourceLocations("classpath:/static/");
//
// 		registry.addResourceHandler("/upload/**")
// 			.addResourceLocations("file:///Users/da-eunlee/Desktop/uploads/");
//
// 	}
//
// }