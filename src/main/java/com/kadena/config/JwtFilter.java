package com.kadena.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kadena.dto.TokenData;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import static java.util.Collections.emptyList;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JwtFilter extends GenericFilterBean {

	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);

			chain.doFilter(req, res);
		} else {

			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header");
			}

			final String token = authHeader.substring(7);

			try {
				final Claims claims = Jwts.parser().setSigningKey("FxCx5JXpXU6N2y6PzS4j35zuQGLvnuUG").parseClaimsJws(token).getBody();
        request.setAttribute("claims", claims);
        String reqString = request.getAttribute("claims").toString();
        Gson gson = new GsonBuilder().create();
        TokenData tokenData = gson.fromJson(reqString, TokenData.class);
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tokenData, null);
        authenticationToken.setDetails(claims);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        
			} catch (final SignatureException e) {
				throw new ServletException("Invalid token");
			}

			chain.doFilter(req, res);
		}
	}
}
