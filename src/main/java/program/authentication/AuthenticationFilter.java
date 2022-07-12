package program.authentication;
import java.io.IOException;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter{
    private String principalRequestHeader;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super("/**");
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> apiKeyOptional = Optional.ofNullable(request.getHeader("key"));
        if (apiKeyOptional.isEmpty()) {
            return getAuthenticationManager().authenticate(new PreAuthenticatedAuthenticationToken(request.getLocalAddr(), null));
        }
        //ApiKeyAuthenticationToken token = apiKeyOptional.map(ApiKeyAuthenticationToken::new).orElse(new ApiKeyAuthenticationToken());
        return getAuthenticationManager().authenticate(new PreAuthenticatedAuthenticationToken(request.getLocalAddr(), apiKeyOptional.get()));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult)
            throws IOException, ServletException {

        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}