//package com.infosys.directory;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@SuppressWarnings("deprecation")
//@EnableWebMvc
//@Configuration
//@ComponentScan(basePackages = {" com.infosys.directory.service"})
//public class DirectoryWebConfig extends WebMvcConfigurerAdapter {
//
//    @Override
//    public void configureMessageConverters(
//        List<HttpMessageConverter<?>> converters) {
//        converters.add(mappingJackson2HttpMessageConverter());
//        super.configureMessageConverters(converters);
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter 
//        mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter jsonConverter = new 
//            MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper() 
//        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jsonConverter.setObjectMapper(objectMapper);
//        return jsonConverter;
//    }
//}
