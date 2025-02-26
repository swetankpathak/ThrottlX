package ThrottlX.ThrottlX.util;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRateLimiter {
    private final Map<String, Bucket> userBuckets = new ConcurrentHashMap<>();
    public Bucket getBucketForUser(String userId){
        return userBuckets.computeIfAbsent(userId, key-> createNewBucket());
    }
    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}
