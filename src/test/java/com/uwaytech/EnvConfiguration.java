package com.uwaytech;

import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
@Configuration
public class EnvConfiguration {
    static {
        System.setProperty("workerId", "7");
    }
}
