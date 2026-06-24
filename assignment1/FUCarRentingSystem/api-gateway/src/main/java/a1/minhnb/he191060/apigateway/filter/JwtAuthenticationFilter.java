package a1.minhnb.he191060.apigateway.filter;

import a1.minhnb.he191060.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        
        // Bypass auth routes and registration
        if (path.startsWith("/api/auth") || path.startsWith("/eureka") || path.equals("/api/customers/register")) {
            return chain.filter(exchange);
        }

        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            return onError(exchange, "Missing authorization header", HttpStatus.UNAUTHORIZED);
        }

        String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader = authHeader.substring(7);
        } else {
            return onError(exchange, "Invalid authorization header", HttpStatus.UNAUTHORIZED);
        }

        try {
            jwtUtil.validateToken(authHeader);
            String role = jwtUtil.extractRole(authHeader);
            String email = jwtUtil.extractEmail(authHeader);
            exchange = exchange.mutate().request(
                    exchange.getRequest().mutate()
                            .header("X-User-Role", role)
                            .header("X-User-Email", email)
                            .build()
            ).build();
        } catch (Exception e) {
            return onError(exchange, "Unauthorized access", HttpStatus.UNAUTHORIZED);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
