package com.rok.sandbox.mod071.restaurant;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by roman.kulikov on 6/13/2017.
 * All rights reserved =D
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.rok.sandbox.mod071.restaurant.")
public class AppConfig {

}
