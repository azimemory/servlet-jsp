package com.kh.toy.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		String[] uriArr = httpRequest.getRequestURI().split("/");
		
		if(uriArr.length > 0) {
			switch(uriArr[1]) {
			case "member" :
				switch(uriArr[2]) {
				case "mypage" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.UNAUTHORIZED_PAGE);
					}
					break;
				case "joinimpl" :
					if(session.getAttribute("persistUser") == null) {
						throw new ToAlertException(ErrorCode.UNAUTHORIZED_PAGE);
					}	
					break;
				}
			case "board" :
				switch(uriArr[2]) {
				case "form" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.UNAUTHORIZED_PAGE);
					}
					break;
				case "upload" :
					if(session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.UNAUTHORIZED_PAGE);
					}	
					break;
				}
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
