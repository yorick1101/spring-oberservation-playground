package yorick.poc.spring.observation.repository.interceptor;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;



@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class MyBatisTracingInterceptor implements Interceptor {

    private final Tracer tracer;

    public MyBatisTracingInterceptor(Tracer tracer){
        this.tracer = tracer;
        log.info("MyBatisTracingInterceptor initialized");
    }
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String queryId = mappedStatement.getId();

        // Start a new span for tracing the MyBatis query
        Span newSpan = this.tracer.nextSpan().name(queryId).start();
        log.info("create new span");
        try (Tracer.SpanInScope ws = this.tracer.withSpan(newSpan)) {
            return invocation.proceed();
        } finally {
            newSpan.end();
        }
    }
}
