package com.alejogalizzi.ecommerce.filter;

import com.alejogalizzi.ecommerce.exception.ExpiredToken;
import com.alejogalizzi.ecommerce.jwt.JwtTokenUtil;
import com.alejogalizzi.ecommerce.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");

    String username = null;
    String token = null;

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      token = requestTokenHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(token);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Unable to get JWT token");
      } catch (ExpiredJwtException e) {
        throw new ExpiredToken("Token is expired");
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (jwtTokenUtil.validateToken(token, userDetails.getUsername())) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
  }
}
