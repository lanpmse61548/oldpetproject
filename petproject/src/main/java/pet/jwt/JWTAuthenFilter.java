package pet.jwt;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenFilter extends AbstractAuthenticationProcessingFilter {
	//
	// @Autowired
	// private ISomeBean someBean;

	public JWTAuthenFilter(AuthenticationManager authManager, RequestMatcher matcher) {
		super(matcher);
		setAuthenticationManager(authManager);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);

		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "*");
		// res.setHeader("Access-Control-Expose-Headers", "Authorization");
		// res.setHeader("Access-Control-Request-Headers","Authorization");
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken("admin", "password", Collections.emptyList()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// String a= someBean.getSomeRandomShit();
		// a = "";

		TokenAuthenticationService.addAuthentication(res, auth.getName(), auth.getAuthorities());
	}

}