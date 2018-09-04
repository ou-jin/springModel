package sys.interceptor;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import sys.annotation.noNull;
import sys.domain.Result;

 

public class NoNullInterceptor extends HandlerInterceptorAdapter{
	 //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
     
    	//如果不是映射到方法直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(noNull.class) != null) {
        	noNull  noNullAnnotation=method.getAnnotation(noNull.class);
        	String Str = noNullAnnotation.str();
        	//从httpServletRequest获取注解上指定的参数
        	Object obj = httpServletRequest.getParameter(Str);
        	if(null != obj){
        		return true;
        	}else{
        		httpServletResponse.getWriter().write(JSON.toJSONString(new Result("false","参数缺失")));
        		return false;
        	}
        }else{
        	return true;
        }
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        
    }
}
