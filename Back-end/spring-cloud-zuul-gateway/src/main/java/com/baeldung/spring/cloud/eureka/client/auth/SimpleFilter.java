package com.baeldung.spring.cloud.eureka.client.auth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class SimpleFilter extends ZuulFilter {

	public static final String AUTH_TOKEN_KEY = "AUTH_TOKEN";
	public static final List<String> tokenNotMandatoryCalls = Arrays.asList("login");

	private AuthenticationService authenticationService = new AuthenticationService();

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		preHandle(ctx.getRequest(), ctx.getResponse());
		return null;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
		String reqUri = request.getRequestURI();
		String serviceName = reqUri.substring(reqUri.lastIndexOf("/") + 1, reqUri.length());
		String autheticationToken = request.getHeader(AUTH_TOKEN_KEY);
		// check tocken expiry time, if expired then,call /doLogin again.
		try {
			if (!serviceName.isEmpty() && !tokenNotMandatoryCalls.contains(serviceName)
					&& !authenticationService.validateToken(autheticationToken)) {
				ZuulException zuulException = new ZuulException("Access Denied", HttpStatus.SC_UNAUTHORIZED, serviceName);
				throw new ZuulRuntimeException(zuulException);
			}
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {
			ZuulException zuulException = new ZuulException("Crypto Exception", HttpStatus.SC_INTERNAL_SERVER_ERROR, serviceName);
			throw new ZuulRuntimeException(zuulException);
		}

		return true;
	}

}