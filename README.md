# spring-oberservation-playground
spring-oberservation-playground


#### Mybatis
Needs to add a mybatis interceptor 

#### RestTemplate
if `restTemplate` is created by `RestTemplateBuilder`

https://docs.spring.io/spring-boot/reference/actuator/tracing.html#actuator.micrometer-tracing.propagating-traces

#### FeignClient
Need to add dependency
```
<dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
   <groupId>io.github.openfeign</groupId>
   <artifactId>feign-micrometer</artifactId>
</dependency>
```