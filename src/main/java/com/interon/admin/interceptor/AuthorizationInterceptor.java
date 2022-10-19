package com.interon.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.interon.admin.util.JwtUtils;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String jwt = request.getHeader("authorization");
		
		/*
		 * if(!request.getMethod().matches("OPTIONS")) {
		 * 
		 * }
		 */
		jwtUtils.verifyJwt(jwt);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
