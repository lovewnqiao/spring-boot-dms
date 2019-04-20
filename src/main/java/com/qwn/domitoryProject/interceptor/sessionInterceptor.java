package com.qwn.domitoryProject.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class sessionInterceptor implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 自定义拦截器，添加拦截路径和排除拦截路径
         * addPathPatterns():添加需要拦截的路径
         * excludePathPatterns():添加不需要拦截的路径
         * 在括号中还可以使用集合的形式，如注释部分代码所示
         */
        registry.addInterceptor(new userLoginInterceptor()).
                excludePathPatterns("/user/managerLogin").
                excludePathPatterns("/user/registerpage").
                excludePathPatterns("/user/uregister").
                excludePathPatterns("/user/userLogin").
                excludePathPatterns("/user/helloWord");

          //举例：利用集合的形式
//        List list = new ArrayList();
//        list.add("/user/loginpage");
//        list.add("/user/registerpage");
//        list.add("/user/uregister");
//        list.add("/user/userlogin");
//
//        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**").excludePathPatterns(list);
    }
}
