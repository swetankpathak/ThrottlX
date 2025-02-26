package ThrottlX.ThrottlX.interceptor;

import ThrottlX.ThrottlX.util.GlobalRateLimiter;
import ThrottlX.ThrottlX.util.UserRateLimiter;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    private final GlobalRateLimiter globalRateLimiter;
    private final UserRateLimiter userRateLimiter;

    public RateLimitInterceptor(GlobalRateLimiter globalRateLimiter, UserRateLimiter userRateLimiter) {
        this.globalRateLimiter = globalRateLimiter;
        this.userRateLimiter = userRateLimiter;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("X-User-Id");

        if (userId == null) {
            response.setStatus(400); // Bad Request
            response.getWriter().write("Missing X-User-Id header.");
            return false;
        }

        // Check global rate limit
        if (!globalRateLimiter.isAllowed()) {
            response.setStatus(429); // Too Many Requests
            response.getWriter().write("Global rate limit exceeded. Try again later.");
            return false;
        }
        // Check per-user rate limit
        Bucket userBucket = userRateLimiter.getBucketForUser(userId);
        if (!userBucket.tryConsume(1)) {
            response.setStatus(429);
            response.getWriter().write("User rate limit exceeded. Try again later.");
            return false;
        }

        return true;
    }

    }
