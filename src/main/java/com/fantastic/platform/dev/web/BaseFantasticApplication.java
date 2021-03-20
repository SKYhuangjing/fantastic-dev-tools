package com.fantastic.platform.dev.web;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sky
 * @date 2019-10-24 16:22:48
 * @version $ Id: BaseFantasticApplication.java, v 0.1  sky Exp $
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = WebConstant.BASE_PACKAGE)
public abstract class BaseFantasticApplication {

}
