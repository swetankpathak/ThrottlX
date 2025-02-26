package ThrottlX.ThrottlX.util;

import com.google.common.util.concurrent.RateLimiter;

import org.springframework.stereotype.Component;

@Component
public class GlobalRateLimiter {
    private final RateLimiter rateLimiter;

    public GlobalRateLimiter(){
        this.rateLimiter = RateLimiter.create(10.0);
    }
    public boolean isAllowed(){
        return rateLimiter.tryAcquire();
    }
}
