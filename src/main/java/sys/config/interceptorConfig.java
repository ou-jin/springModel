package sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import sys.interceptor.NoNullInterceptor;
import sys.interceptor.PermissionInterceptor;
 
 
@Configuration
public class interceptorConfig extends WebMvcConfigurerAdapter{
   //新建一个拦截类注入到spring容器
	@Bean
   public HandlerInterceptor getMyInterceptor(){
	     //返回自定义的拦截类
		 return new PermissionInterceptor();
	}
	//将注入到spring中的拦截类添加到拦截链
	public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getMyInterceptor())    //指定拦截器类
                .addPathPatterns("/**");        //指定该类拦截的url
        registry.addInterceptor(new NoNullInterceptor())
        		.addPathPatterns("/**"); 
        //非空字段拦截
        super.addInterceptors(registry);
    }
}
