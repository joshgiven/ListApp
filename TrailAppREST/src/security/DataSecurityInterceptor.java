package security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class DataSecurityInterceptor extends HandlerInterceptorAdapter {
  @Autowired
  SecretKeyGenerator keyGen;

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
    // check if a 'x-access-token' header exists on the request
    if (req.getHeader("x-access-token") != null) {
      // retrieve the jwt from the request header
      String jwt = req.getHeader("x-access-token");
      // parse the jwt's claims using the injected secret key
      Jws<Claims> jws = Jwts.parser()
                          .setSigningKey(keyGen.getSecretKey())
                          .parseClaimsJws(jwt);
      // extract the user id from the claims (cast it from an Object to an int)
      int userId = (int) jws.getBody().get("user_id");
      // add the user_id to the HttpServletRequest's attributes
      req.setAttribute("userId", userId);

      return true;
    }

    // if you got here, the user is not authenticated, redirect to a route which will respond 401
    res.sendRedirect("http://localhost:8080/unauthorized");
    return false;
  }
}