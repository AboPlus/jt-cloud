package com.cy.jt;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new RequestInterceptor() {
            /**在此方法可以通过requestTemplate对象向新的请求中写数据*/
            public void apply(RequestTemplate requestTemplate) {
                //1.获取原有请求中的数据
                ServletRequestAttributes requestAttributes=
                        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request=requestAttributes.getRequest();
                //2.将原有请求中数据添加到新的请求中
                //requestTemplate.header()//向请求头添加数据
                Map<String, String[]> parameterMap = request.getParameterMap();
                if(parameterMap.containsKey("pageCurrent")) {
                    requestTemplate.query("pageCurrent", request.getParameter("pageCurrent"));
                    requestTemplate.query("pageSize", request.getParameter("pageSize"));
                }
            }
        };
    }
}
