# ThrottlX - Distributed Rate Limiter  

🚀 **ThrottlX** is a high-performance, distributed rate-limiting solution designed for modern microservices. It efficiently prevents API abuse by enforcing both **global** and **per-user** request limits using **Guava RateLimiter** and **Bucket4j**.

## 🌟 Features  

✅ **Global & Per-User Rate Limiting** – Ensures fair and controlled API usage.  
✅ **Scalable & High Performance** – Works efficiently in distributed environments.  
✅ **Low Latency** – Uses optimized in-memory algorithms for instant rate enforcement.  
✅ **Token Bucket Approach** – Smooth request handling without sudden rejections.  
✅ **Flexible Configuration** – Allows customization of rate limits as per API needs.  

## 🛠️ Tech Stack  

| Component            | Purpose                                   |
|----------------------|-------------------------------------------|
| **Guava RateLimiter** | Global rate limiting (per service)        |
| **Bucket4j**         | Per-user rate limiting (token bucket)     |
| **Spring Boot**      | API Gateway & Request Interceptor         |
| **In-Memory Caching**| Fast token bucket storage                 |
| **Java 17+**         | Modern Java features for efficiency       |

## ⚡ How It Works  

ThrottlX applies **two levels of rate limiting**:  

1. **Global Rate Limiting**  
   - Controls the overall API request rate across instances.  
   - Ensures smooth traffic flow without congestion.  

2. **Per-User Rate Limiting**  
   - Implements a token bucket per user/IP.  
   - Prevents individual users from overloading the system.  

## 📜 Installation & Usage  

### 1️⃣ Clone the Repository  
```sh
git clone https://github.com/yourusername/ThrottlX.git
cd ThrottlX
```

### 2️⃣ Build & Run the Application  
```sh
./mvnw spring-boot:run
```

### 3️⃣ Test Rate Limiting  
```sh
curl -X GET "http://localhost:8080/api/test"
```

## 📌 Future Enhancements  

✅ Adaptive Rate Limits based on API load  
✅ Customizable Quotas via Configuration  
✅ Integration with API Gateway (Kong / Nginx)  
✅ Monitoring & Metrics for Rate Limits  

## 📜 License  

MIT License. Feel free to contribute!  

### API FLOW
1️⃣ **API Request Starts** 

A client sends a request to /api/data.
2️⃣ **RateLimitInterceptor Intercepts Request** 

Before reaching the controller, the interceptor checks if the request should be rate-limited.
3️⃣ **Check Global Rate Limit (GlobalRateLimiter)** 

The system checks if the global limit (e.g., 10 requests per second) is exceeded.
Example: If only 8 requests have been made, it proceeds. If 11 requests have been made, it blocks the request.
IF BLOCKED → Return HTTP 429 (Too Many Requests).

4️⃣ **Check Per-User Rate Limit (UserRateLimiter)** 

If the global rate limit is not exceeded, the system checks if the individual user has exceeded their limit (e.g., 5 requests per minute).
Example: If user "user123" has made 3 requests, it proceeds. If user "user123" has made 6 requests, it blocks the request.
IF BLOCKED → Return HTTP 429 (Too Many Requests).

5️⃣ **Allow Request → Forward to Controller** 

If both global and user limits are not exceeded, the request is forwarded to the controller.
6️⃣ **API Responds with Data** 

The controller sends the API response back to the client.
---  
 
