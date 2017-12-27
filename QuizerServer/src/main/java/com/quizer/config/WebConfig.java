package com.quizer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author UZAIR
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.quizer"})
public class WebConfig {
     
}
