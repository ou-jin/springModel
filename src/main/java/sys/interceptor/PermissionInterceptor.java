package sys.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

import sys.annotation.permission;
import sys.dao.MysqlDAOImplement;
import sys.domain.Result;
 
@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter{
	@Resource(name = "MysqlDAOImplement")
	private MysqlDAOImplement dao;
    //在请求处理之前进行调用（Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
     
    	//如果不是映射到方法直接通过
        if (!(o instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        if (method.getAnnotation(permission.class) != null) {
        	//获取拥有权限注解的方法中的权限值
        	permission  permissionAnnotation=method.getAnnotation(permission.class);
        	String permStr = permissionAnnotation.perm();
        	//获取本次请求中所携带的token信息所属用户的权限列表
        	 //获取token
		    String token=httpServletRequest.getHeader("Token");
		    //获取该token的用户信息的权限列表
		    List<String> list = (List<String>) dao.findForObject("tokenMapper.queryMeauByToken", token);
		  //  List<String> list= tokenDao.queryMeauByToken(token);
            //如果该用户所属权限列表包含该权限值则正常请求	
		    if(list.contains(permStr)){
            		 return true;
            	 }else{
            		 httpServletResponse.getWriter().write(JSON.toJSONString(new Result("false","该用户未拥有请求权限")));
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
