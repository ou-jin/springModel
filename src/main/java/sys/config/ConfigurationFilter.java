package sys.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


import sys.dao.MysqlDAOImplement;
import sys.domain.Result;
import sys.domain.sysUserInfo;

@Component
//拦截所有路径
@WebFilter(urlPatterns = { "/**" }, filterName = "tokenAuthorFilter")
public class ConfigurationFilter implements Filter{
	@Resource(name = "MysqlDAOImplement")
	private MysqlDAOImplement dao;
	//定义不需要拦截的url
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(
            		"webjars",
            		"druid", 
            		"swagger",
            		"v2",
            		"swagger-ui.html", 
            		"swagger-resources",
            		"configuration",
            		"images"
            		)
            )
			);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletResponse responses = (HttpServletResponse) response;
		 HttpServletRequest requestes = (HttpServletRequest) request;
		// 允许哪些Origin发起跨域请求,nginx下正常
	        // response.setHeader( "Access-Control-Allow-Origin", config.getInitParameter( "AccessControlAllowOrigin" ) );
		 	responses.setHeader( "Access-Control-Allow-Origin", "*" );
	        // 允许请求的方法
		 	responses.setHeader( "Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT" );
	        // 多少秒内，不需要再发送预检验请求，可以缓存该结果
		 	responses.setHeader( "Access-Control-Max-Age", "3600" );
	        // 表明它允许跨域请求包含xxx头
		 	responses.setHeader( "Access-Control-Allow-Headers", "content-type,token" );
	        //是否允许浏览器携带用户身份信息（cookie）
		    if (requestes.getMethod().equals( "OPTIONS" )) {
		    	responses.setStatus( 200 );
	            return;
	        }
		    //将不需要拦截的url过滤
		    String path = requestes.getRequestURI().substring(requestes.getContextPath().length()).replaceAll("[/]+$", "");
		    String paths[] =path.split("/");
		    boolean allowedPath = ALLOWED_PATHS.contains(paths[1]);
	        if (allowedPath) {
	            chain.doFilter(requestes, responses);
	        }else{
	        try{
	        //获取token
		    String token=requestes.getHeader("Token");
		    //获取该token的用户信息
		    sysUserInfo  uInfo= (sysUserInfo)dao.findForObject("tokenMapper.queryByToken", token);
		    if(uInfo!=null){
		    	chain.doFilter(requestes, responses);//到下一个链
		    }else{
		    	responses.getWriter().write(JSON.toJSONString(new Result("false","不具有权限")));
                return;
		    }
	        }catch(Exception e){
	        	responses.getWriter().write(JSON.toJSONString(new Result("false","验证失败")));
	        }
	        }
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
