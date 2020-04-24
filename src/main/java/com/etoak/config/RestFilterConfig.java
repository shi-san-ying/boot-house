package com.etoak.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

//配置支持同步请求（表单方式的） 发送rest请求过滤器
//将post请求转化成put请求
@Configuration
public class RestFilterConfig  {
    //注册HiddenHttpMethodFilter
    //作用：将post请求转化成put delete 请求
    //要求：
    //1.必须是表单的提交方式
    //2.表单必须又一个隐藏域  name属性值是_method  value属性是rest请求的方式（如put delete）
    @Bean
    public FilterRegistrationBean<HiddenHttpMethodFilter> restFilter(){
        FilterRegistrationBean<HiddenHttpMethodFilter> restFilter=
                new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        restFilter.addUrlPatterns("/*");
        return restFilter;
    }

//HiddenHttpMethodFilter 源代码
//
//    public class HiddenHttpMethodFilter extends OncePerRequestFilter {
//
//        /** Default method parameter: {@code _method} */
//        public static final String DEFAULT_METHOD_PARAM = "_method";
//
//        private String methodParam = DEFAULT_METHOD_PARAM;
//
//
//        /**
//         * Set the parameter name to look for HTTP methods.
//         * @see #DEFAULT_METHOD_PARAM
//         */
//        public void setMethodParam(String methodParam) {
//            Assert.hasText(methodParam, "'methodParam' must not be empty");
//            this.methodParam = methodParam;
//        }
//
//        @Override
//        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//                throws ServletException, IOException {
//
//            HttpServletRequest requestToUse = request;
//            //如果这是一个POST请求
//            if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
//                //接着获取参数值比如：PUT、DELETE等
//                String paramValue = request.getParameter(this.methodParam);
//                //封装成的相应的请求
//                if (StringUtils.hasLength(paramValue)) {
//                    requestToUse = new HttpMethodRequestWrapper(request, paramValue);
//                }
//            }
//            //最后将请求发送给下一个filter
//            filterChain.doFilter(requestToUse, response);
//        }
//
//
//        /**
//         * Simple {@link HttpServletRequest} wrapper that returns the supplied method for
//         * {@link HttpServletRequest#getMethod()}.
//         */
//        private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
//
//            private final String method;
//　　　　
//            public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
//                super(request);
//                this.method = method.toUpperCase(Locale.ENGLISH);
//            }
//
//            @Override
//            public String getMethod() {
//                return this.method;
//            }
//        }

}
