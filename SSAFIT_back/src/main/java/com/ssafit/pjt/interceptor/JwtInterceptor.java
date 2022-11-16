package com.ssafit.pjt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafit.pjt.Util.JwtUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	 
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(request.getMethod().equals("OPTIONS"))
			return true;
		
		final String token = request.getHeader("access-token");
		
		if(token != null) {
			jwtUtil.valid(token);
			return true;
		}
		
		throw new Exception("유효하지 않은 접근입니다.");
	}
}
