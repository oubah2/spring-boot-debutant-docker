package org.sid.sec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.sid.domaine.SecurityParams;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import com.auth0.jwt.algorithms.Algorithm;

public class JWTAuthenticationFiter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin,Accept,Content-Type,Access-control-request-method,Access-control-request-Headers,x-requested-with,authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,Access-Control-Allow-Credentials,authorization");
        response.addHeader("Access-Control-Allow-Methods", "OPTIONS, HEAD, GET, POST, PATCH, PUT, DELETE");
        if (request.getMethod().equals("OPTIONS"))
            response.setStatus(HttpServletResponse.SC_OK);

        else if (request.getRequestURI().equals("/login")) {

            filterChain.doFilter(request, response);
            return;
        } else {
            String jwt = request.getHeader(SecurityParams.JWT_HEADER);

            if (jwt == null || !jwt.startsWith(SecurityParams.TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.PRIVATE_SECRET)).build();
            String jwtToken = jwt.substring(SecurityParams.TOKEN_PREFIX.length());
            System.out.println("Toekn= " + jwtToken);
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            System.out.println("Toekn= " + decodedJWT);
            String userName = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
            Collection<GrantedAuthority> autorities = new ArrayList<>();
            roles.forEach((r -> {
                autorities.add(new SimpleGrantedAuthority(r));
            }));
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userName, null, autorities);
            SecurityContextHolder.getContext().setAuthentication(user);
            filterChain.doFilter(request, response);

        }

    }
}
