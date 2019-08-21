package com.wissensalt.rnd.sbed.util.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * @author : <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 * @since : 2019-08-05
 **/
public interface IRequestLoggerAspect {

    @Around("execution(@RequestLogger * *(..)) && @annotation(requestLogger)")
    Object logRequest(ProceedingJoinPoint joinPoint, RequestLogger requestLogger) throws Throwable;

    String getRequestBody(final ContentCachingRequestWrapper wrapper);

    ContentCachingRequestWrapper getWrapper(ProceedingJoinPoint joinPoint);
}
