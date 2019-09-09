package pet.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security
            .authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;

class TokenAuthenticationService {
  static final long EXPIRATIONTIME = 864_000_000; // 10 days
  static final String SECRET = "ThisIsASecretAdJadsdadFdsdsaDA";
  static final String TOKEN_PREFIX = "Bearer";
  static final String HEADER_STRING = "Authorization";

  static void addAuthentication(HttpServletResponse res, String username, Collection<? extends GrantedAuthority> authorities) {
	 
	  Claims claims = Jwts.claims().setSubject(username);
	  claims.put("scopes", authorities.stream().map(s -> s.toString()).collect(Collectors.toList()));
	 String JWT = Jwts.builder().setClaims(claims)
        //.setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    
    try {
    	RedisUtil.INSTANCE.sadd(username, username);
	} catch (Exception e) {
		Logger.getLogger (TokenAuthenticationService.class.getName()).log(Level.WARNING, e.getMessage(), e);
	}
  }

  static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(HEADER_STRING);
    if (token != null && !token.equals("")) {
      // parse the token.
      String user = Jwts.parser()
          .setSigningKey(SECRET)
          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody()
          .getSubject();
//      List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
//      grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
      List<String> scopes = Jwts.parser()
              .setSigningKey(SECRET)
              .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
              .getBody().get("scopes", List.class);
      List<GrantedAuthority> authorities = scopes.stream()
              .map(authority -> new SimpleGrantedAuthority(authority))
              .collect(Collectors.toList());
      RedisUtil.INSTANCE.sismember(user, user);
      return user != null ?
          new UsernamePasswordAuthenticationToken(user, null, authorities) :
          null;
    }
    return null;
  }
}